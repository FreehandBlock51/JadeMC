package net.dc.jademc.block;

import net.dc.jademc.Archive;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class GreenCrystalOre extends OreBlock {
    static final AbstractBlock.Properties PROPERTIES = AbstractBlock.Properties.of(Material.STONE)
    .harvestTool(ToolType.PICKAXE)
    .harvestLevel(2)
    .requiresCorrectToolForDrops()
    .strength(5);

    public GreenCrystalOre() {
        super(PROPERTIES);

        this.setRegistryName("green_crystal_ore");
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 5;
    }

    public static class Item extends BlockItem {
        static final Item.Properties PROPERTIES = new Item.Properties()
            .tab(ItemGroup.TAB_BUILDING_BLOCKS)
            .craftRemainder(Archive.JADE_SHARD)
            .stacksTo(10);
        public Item() {
            super(Archive.GREEN_CRYSTAL_ORE, PROPERTIES);

            this.setRegistryName("green_crystal_ore");
        }
    }
}
