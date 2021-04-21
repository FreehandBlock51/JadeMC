package net.dc.jademc.block;

import net.dc.jademc.Archive;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class EnderiteOre extends OreBlock {
    static final AbstractBlock.Properties PROPERTIES = AbstractBlock.Properties.of(Material.STONE)
    .harvestTool(ToolType.PICKAXE)
    .harvestLevel(5)
    .requiresCorrectToolForDrops()
    .strength(25);
    static final String NAME = "enderite_ore";

    public EnderiteOre() {
        super(PROPERTIES);

        this.setRegistryName(NAME);
    }

    public static class Item extends BlockItem {
        static final Item.Properties PROPERTIES = new Item.Properties()
            .tab(ItemGroup.TAB_BUILDING_BLOCKS);
        public Item() {
            super(Archive.ENDERITE_ORE, PROPERTIES);

            this.setRegistryName(NAME);
        }
    }
}
