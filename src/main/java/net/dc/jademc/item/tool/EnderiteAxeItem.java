package net.dc.jademc.item.tool;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public class EnderiteAxeItem extends AxeItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_TOOLS)
        .fireResistant()
        .rarity(Rarity.EPIC);
    static final float damage = 15.0F;
    static final float speedBonus = -1.5F;
    
    public EnderiteAxeItem() {
        super(new EnderiteItemTier(), damage, speedBonus, PROPERTIES);

        this.setRegistryName("enderite_axe");
    }
}
