package net.dc.jademc.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;

public class JadePickaxeItem extends PickaxeItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_TOOLS)
        .fireResistant();
    static final int damage = 1;
    static final float speedBonus = -1.4F; 

    public JadePickaxeItem() {
        super(new JadeItemTier(), damage, speedBonus, PROPERTIES);
        

        this.setRegistryName("jade_pickaxe");
    }
}
