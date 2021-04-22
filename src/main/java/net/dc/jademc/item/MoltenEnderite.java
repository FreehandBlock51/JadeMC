package net.dc.jademc.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public class MoltenEnderite extends Item {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_MATERIALS)
        .rarity(Rarity.RARE);
    
    public MoltenEnderite() {
        super(PROPERTIES);

        this.setRegistryName("molten_enderite");
    }
}
