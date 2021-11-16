package net.dc.jademc.block;

import java.util.List;

import javax.annotation.Nullable;

import net.dc.jademc.Archive;
import net.dc.jademc.block.TransportHub.ItemTransportedEvent;
import net.dc.jademc.block.TransportHub.Transportable;
import net.dc.jademc.tileentity.PackageTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Package extends ContainerBlock {
    static final Properties PROPERTIES = Properties.of(new Material.Builder(MaterialColor.COLOR_ORANGE).build())
        .harvestLevel(2)
        .strength(3, 2);

    public Package() {
        super(PROPERTIES);

        this.setRegistryName("package");
    }

    static final float[] BEACON_COLOR_MULTIPLIER = new float[] {0xFF / 255, 0xA5 / 255, 0x00 / 255};
    @Override
    public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
        return BEACON_COLOR_MULTIPLIER;
    }
    
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Archive.PACKAGE_TYPE.create();
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return createTileEntity(null, world);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide()) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof PackageTileEntity) {
                player.openMenu((PackageTileEntity)tileentity);
            }

            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof PackageTileEntity) {
            PackageTileEntity packageTileEntity = (PackageTileEntity)tileentity;
            if (!world.isClientSide && player.isCreative() && !packageTileEntity.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                CompoundNBT compoundnbt = packageTileEntity.saveToTag(new CompoundNBT());
                if (!compoundnbt.isEmpty()) {
                    itemstack.addTagElement("BlockEntityTag", compoundnbt);
                }

                if (packageTileEntity.hasCustomName()) {
                    itemstack.setHoverName(packageTileEntity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                world.addFreshEntity(itementity);
            } else {
                packageTileEntity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
           TileEntity tileentity = world.getBlockEntity(pos);
           if (tileentity instanceof PackageTileEntity) {
              ((PackageTileEntity)tileentity).setCustomName(stack.getHoverName());
           }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> components, ITooltipFlag flag) {
      super.appendHoverText(stack, reader, components, flag);
      CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");
      if (compoundnbt != null) {
         if (compoundnbt.contains("LootTable", 8)) {
            components.add(new StringTextComponent("???????"));
         }

         if (compoundnbt.contains("Items", 9)) {
            NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);

            for(ItemStack itemstack : nonnulllist) {
               if (!itemstack.isEmpty()) {
                    IFormattableTextComponent iformattabletextcomponent = itemstack.getHoverName().copy();
                    iformattabletextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
                    components.add(iformattabletextcomponent);
               }
            }
         }
      }
   }

    // Item
    @Transportable
    public static class Item extends BlockItem {
        static final Properties PROPERTIES = new Properties()
            .stacksTo(16)
            .tab(ItemGroup.TAB_DECORATIONS);

        public Item() {
            super(Archive.PACKAGE, PROPERTIES);

            this.setRegistryName("package");
        }

        @SubscribeEvent
        public static void onItemTransported(ItemTransportedEvent event) {
            if (event.getItems().getItem().getClass() == Item.class) {
                event.getItems().addTagElement("Recipient", StringNBT.valueOf(event.getRecipient().getStringUUID()));
            }
        }
    }
}
