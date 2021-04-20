package net.dc.jademc.inventory;

import net.minecraft.inventory.IInventory;

public interface IInventoryProvider<T extends IInventory> {
    public T getInventory();
    public void modifyInventory(Modifier<T> modifier);

    @FunctionalInterface
    public static interface Modifier<T extends IInventory> {
        public void modify(T inv);
    }
}
