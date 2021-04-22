package net.dc.jademc.block;

import net.dc.jademc.Archive;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public class EnderiteBlock extends Block {
    static final AbstractBlock.Properties PROPERTIES = AbstractBlock.Properties.of(Material.HEAVY_METAL);
    
    public EnderiteBlock() {
        super(PROPERTIES);

        this.setRegistryName("enderite_block");
    }

    public static class Item extends BlockItem {
        static final Item.Properties PROPERTIES = new Item.Properties()
            .tab(ItemGroup.TAB_BUILDING_BLOCKS)
            .fireResistant()
            .rarity(Rarity.EPIC);
        
        public Item() {
            super(Archive.ENDERITE_BLOCK, PROPERTIES);

            this.setRegistryName("enderite_block");
        }
    }
}
