package net.dc.jademc.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;

public class EnderitePickaxeItem extends PickaxeItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_TOOLS)
        .fireResistant()
        .rarity(Rarity.EPIC);
    static final int damage = 2;
    static final float speedBonus = -1.4F;

    public EnderitePickaxeItem() {
        super(new EnderiteItemTier(), damage, speedBonus, PROPERTIES);

        this.setRegistryName("enderite_pickaxe");
    }
}
