package net.dc.jademc.item;

import net.dc.jademc.Archive;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ImpureCrystalItem extends Item {
    static final Item.Properties PROPERTIES = new Item.Properties()
    .tab(ItemGroup.TAB_MATERIALS)
    .craftRemainder(Archive.JADE_SHARD);

    public ImpureCrystalItem() {
        super(PROPERTIES);
        this.setRegistryName("impure_crystal");
    }
}
