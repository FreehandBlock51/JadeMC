package net.dc.jademc.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.AxeItem;

public class JadeAxeItem extends AxeItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_TOOLS)
        .fireResistant();
        static final float damage = 12.0F;
        static final float speedBonus = -1.55F;

    public JadeAxeItem() {
        super(new JadeItemTier(), damage, speedBonus, PROPERTIES);
        

        this.setRegistryName("jade_axe");
    }
}
