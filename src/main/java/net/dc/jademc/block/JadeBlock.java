package net.dc.jademc.block;

import net.dc.jademc.Archive;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public class JadeBlock extends Block {
    static final Properties PROPERTIES = Properties.copy(Blocks.EMERALD_BLOCK);

    public JadeBlock() {
        super(PROPERTIES);

        this.setRegistryName("jade_block");
    }

    public static class Item extends BlockItem {
        static final Properties PROPERTIES = new Properties()
            .fireResistant()
            .tab(ItemGroup.TAB_BUILDING_BLOCKS);

        public Item() {
            super(Archive.JADE_BLOCK, PROPERTIES);

            this.setRegistryName("jade_block");
        }
    }
}
