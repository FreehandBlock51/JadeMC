package net.dc.jademc.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class JadeSwordItem extends SwordItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_COMBAT)
        .fireResistant();
        static final int damage = 6;
        static final float speedBonus = -1.2F;

    public JadeSwordItem() {
        super(new JadeItemTier(), damage, speedBonus, PROPERTIES);

        this.setRegistryName("jade_sword");
    }
}
