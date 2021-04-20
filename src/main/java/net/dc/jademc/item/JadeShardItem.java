package net.dc.jademc.item;

import net.dc.jademc.Archive;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class JadeShardItem extends Item {
    static final Item.Properties PROPERTIES = new Item.Properties()
    .tab(ItemGroup.TAB_MATERIALS)
    .craftRemainder(Archive.JADE)
    .stacksTo(99);

    public JadeShardItem() {
        super(PROPERTIES);
        this.setRegistryName("jade_shard");
    }
}
