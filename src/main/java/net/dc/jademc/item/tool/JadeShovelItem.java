package net.dc.jademc.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;

public class JadeShovelItem extends ShovelItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_TOOLS)
        .fireResistant();
    static final float damage = 3.0F;
    static final float speedBonus = -1.5F;
    
    public JadeShovelItem() {
        super(new JadeItemTier(), damage, speedBonus, PROPERTIES);
        

        this.setRegistryName("jade_shovel");
    }
}
