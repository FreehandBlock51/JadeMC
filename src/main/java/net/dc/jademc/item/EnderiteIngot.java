package net.dc.jademc.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public class EnderiteIngot extends Item {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_MATERIALS)
        .fireResistant()
        .rarity(Rarity.EPIC);
    
    public EnderiteIngot() {
        super(PROPERTIES);

        this.setRegistryName("enderite_ingot");
    }
}
