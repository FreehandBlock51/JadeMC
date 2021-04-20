package net.dc.jademc.item.tool;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;

public class JadeHoeItem extends HoeItem {
    static final Properties PROPERTIES = new Properties()
        .tab(ItemGroup.TAB_MATERIALS)
        .fireResistant();
    static final int damage = -1;
    static final float speedBonus = -0.5F;

    public JadeHoeItem() {
        super(new JadeItemTier(), damage, speedBonus, PROPERTIES);

        this.setRegistryName("jade_hoe");
    }
}
