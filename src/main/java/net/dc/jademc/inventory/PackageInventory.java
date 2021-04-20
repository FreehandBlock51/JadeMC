package net.dc.jademc.inventory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

@Deprecated
public class PackageInventory extends Inventory {
    static final int INV_SIZE = 9;
    
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PackageInventory() {
        super(INV_SIZE);
    }
    private PackageInventory(ItemStack[] items) {
        this();
        if (items.length > INV_SIZE) {
            Throwable warning = new Throwable(
                "The given array has a length of " + items.length + ", which is larger than " + INV_SIZE + 
                ".  ItemStacks at the end of the array will not be added to the inventory.");
            LOGGER.warn(warning.getMessage(), warning.fillInStackTrace());
        }
        for (int i = 0; i < Math.min(9, items.length); i++) {
            this.addItem(items[i]);
        }
    }
    public PackageInventory(ItemStack item) {
        this(new ItemStack[] {item});
    }
    public PackageInventory(ItemStack i0, ItemStack i1) {
        this(new ItemStack[] {i0, i1});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2) {
        this(new ItemStack[] {i0, i1, i2});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2, ItemStack i3) {
        this(new ItemStack[] {i0, i1, i2, i3});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4) {
        this(new ItemStack[] {i0, i1, i2, i3, i4});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5) {
        this(new ItemStack[] {i0, i1, i2, i3, i4, i5});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6) {
        this(new ItemStack[] {i0, i1, i2, i3, i4, i5, i6});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack i7) {
        this(new ItemStack[] {i0, i1, i2, i3, i4, i5, i6, i7});
    }
    public PackageInventory(ItemStack i0, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack i7, ItemStack i8) {
        this(new ItemStack[] {i0, i1, i2, i3, i4, i5, i6, i7, i8});
    }
}
