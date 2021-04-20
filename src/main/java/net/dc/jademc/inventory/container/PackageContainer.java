package net.dc.jademc.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

@Deprecated
public class PackageContainer extends Container {
    final IInventory inventory;

    public PackageContainer(int containerId, PlayerInventory pInventory) {
        this(containerId, new Inventory(27), pInventory);
     }
    public PackageContainer(int containerId, IInventory inventory, PlayerInventory pInventory) {
        super(ContainerType.GENERIC_3x3, containerId);

        checkContainerSize(inventory, 9);
        this.inventory = inventory;
        inventory.startOpen(pInventory.player);
        for(int k = 0; k < 3; ++k) {
            for(int l = 0; l < 3; ++l) {
                this.addSlot(new Slot(inventory, l + k * 9, 8 + l * 18, 18 + k * 18));
            }
        }

        for(int i1 = 0; i1 < 3; ++i1) {
            for(int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(pInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }

        for(int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(pInventory, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int slotId) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if (slot != null && slot.hasItem()) {
           ItemStack itemstack1 = slot.getItem();
           itemstack = itemstack1.copy();
           if (slotId < this.inventory.getContainerSize()) {
              if (!this.moveItemStackTo(itemstack1, this.inventory.getContainerSize(), this.slots.size(), true)) {
                 return ItemStack.EMPTY;
              }
           } else if (!this.moveItemStackTo(itemstack1, 0, this.inventory.getContainerSize(), false)) {
              return ItemStack.EMPTY;
           }
  
           if (itemstack1.isEmpty()) {
              slot.set(ItemStack.EMPTY);
           } else {
              slot.setChanged();
           }
        }
  
        return itemstack;
     }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }
}
