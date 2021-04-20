package net.dc.jademc.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class JadeItem extends Item {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_MATERIALS)
        .stacksTo(31)
        .fireResistant();

    public JadeItem() {
        super(PROPERTIES);
        this.setRegistryName("jade");
    }

    
}
