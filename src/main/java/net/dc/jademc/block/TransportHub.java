package net.dc.jademc.block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.dc.jademc.Archive;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.Event;

public class TransportHub extends Block {
    static final Properties PROPERTIES = Properties.of(Material.METAL)
        .harvestTool(ToolType.PICKAXE)
        .harvestLevel(2)
        .strength(7)
        .requiresCorrectToolForDrops()
        .noOcclusion();
    static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST);

    static final Logger LOGGER = LogManager.getLogger();
    
    public TransportHub() {
        super(PROPERTIES);

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.WEST));

        this.setRegistryName("transport_hub");
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.stateDefinition.any().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos,
            PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        CompoundNBT save = player.getPersistentData();
        ListNBT gifts = save.getList("gifts", 10);
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.getItem() == Items.AIR) {
            try {
                for (INBT nbt : gifts) {
                    ItemStack gift = ItemStack.of((CompoundNBT)nbt);
                    player.drop(gift, true);
                    gifts.remove(nbt);
                }
            } catch (NoSuchElementException e) { // no items to give
                LOGGER.catching(e);
            }
        }
        else if (handStack.getItem().is(Archive.NoRegistry.VANILLA_TRANSPORT_ITEMS) || handStack.getItem().getClass().getAnnotation(Transportable.class) != null) {
            ITextComponent name = handStack.getHoverName();
            if (!handStack.hasCustomHoverName() || !(name instanceof StringTextComponent)) {
                player.displayClientMessage(new TranslationTextComponent("message.jademc.transport_hub.invalid_name"), true);
                return ActionResultType.CONSUME;
            }
            StringTextComponent itemDisplayName = (StringTextComponent) name;
            UUID rid = PlayerEntity.createPlayerUUID(itemDisplayName.getText());
            LOGGER.info("Display name: " + itemDisplayName.getText() + ", UUID: " + rid);
            PlayerEntity recvPlayer = null;
            for (PlayerEntity p : world.players()) {
                String dn = p.getGameProfile().getName();
                LOGGER.info("got a player (Display name: " + dn + ", UUID: " + p.getGameProfile().getId() + ", " + p.getUUID() + ")");
                if (p.getUUID().equals(rid) || itemDisplayName.getText().equals(dn)) {
                    recvPlayer = p;
                    break;
                }
            }
            if (recvPlayer == null) {
                player.displayClientMessage(new TranslationTextComponent("message.jademc.transport_hub.invalid_name"), true);
                return ActionResultType.FAIL;
            }
            CompoundNBT nbt = new CompoundNBT();
            handStack.setHoverName(player.getName());
            handStack.save(nbt);
            player.setItemInHand(hand, new ItemStack(Items.AIR));
            recvPlayer.getPersistentData().getList("gifts", 10).add(nbt);
            player.displayClientMessage(new TranslationTextComponent("message.jademc.transport_hub.success"), true);
            ItemTransportedEvent event = new ItemTransportedEvent(handStack, player, recvPlayer);
            MinecraftForge.EVENT_BUS.post(event);
            
        }
        else {
            player.displayClientMessage(new TranslationTextComponent("message.jademc.transport_hub.invalid_item"), true);
            return ActionResultType.FAIL;
        }
        save.put("gifts", gifts);
        return ActionResultType.CONSUME;
    }

    public static class Item extends BlockItem {
        static final Properties PROPERTIES = new Properties()
            .stacksTo(32)
            .tab(ItemGroup.TAB_REDSTONE);
        public Item() {
            super(Archive.TRANSPORT_HUB, PROPERTIES);

            this.setRegistryName("transport_hub");
        }
    }

    /**
     * The event class for when an item is transported using the transport hub
     */
    public static class ItemTransportedEvent extends Event {
        /**
         * The {@linkplain ItemStack} being transported
         */
        private final ItemStack ITEMS;
        /**
         * The {@link PlayerEntity player} sending the items
         */
        private final PlayerEntity SENDER;
        /**
         * The {@link PlayerEntity player} recieving the items
         */
        private final PlayerEntity RECIEVER;

        /**
         * Initilizes this class with the specified stack, sender, and recipient
         * @param stack the value for {@linkplain #ITEMS}
         * @param sender the value for {@linkplain #SENDER}
         * @param reciever the value for {@linkplain #RECIEVER}
         */
        public ItemTransportedEvent(ItemStack stack, PlayerEntity sender, PlayerEntity reciever) {
            this.ITEMS = stack;
            this.SENDER = sender;
            this.RECIEVER = reciever;
        }

        /**
         * Gets the {@link ItemStack items} being sent
         * @return {@linkplain #ITEMS}
         */
        public ItemStack getItems() {
            return this.ITEMS;
        }
        /**
         * Gets the {@link PlayerEntity player} that sent the items
         * @return {@linkplain #SENDER}
         */
        public PlayerEntity getSender() {
            return this.SENDER;
        }
        /**
         * Gets the {@link PlayerEntity player} that will be recieving the items
         * @return {@linkplain #RECIEVER}
         */
        public PlayerEntity getReciever() {
            return this.RECIEVER;
        }
        /**
         * @see #getReciever()
         */
        public PlayerEntity getRecipient() {
            return this.getReciever();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public static @interface Transportable {}
}
