package net.dc.jademc.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;

public class EnderiteShovelItem extends ShovelItem {
    static final Item.Properties PROPERTIES = new Item.Properties()
        .tab(ItemGroup.TAB_TOOLS)
        .fireResistant()
        .rarity(Rarity.EPIC);
    static final float damage = 3.0F;
    static final float speedBonus = -1.5F;

    public EnderiteShovelItem() {
        super(new EnderiteItemTier(), damage, speedBonus, PROPERTIES);

        this.setRegistryName("enderite_shovel");
    }
}
