package net.dc.jademc.tileentity;

import java.util.stream.IntStream;

import net.dc.jademc.Archive;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PackageTileEntity extends LockableLootTileEntity implements ISidedInventory {
    NonNullList<ItemStack> itemStacks = NonNullList.withSize(9, ItemStack.EMPTY);
    final int[] SLOTS = IntStream.range(0, 9).toArray();

    public PackageTileEntity() {
        super(
            Archive.PACKAGE_TYPE
        );
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        return this.saveToTag(nbt);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.loadFromTag(nbt);
    }
    
    public void loadFromTag(CompoundNBT nbt) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt) && nbt.contains("Items", 9)) {
           ItemStackHelper.loadAllItems(nbt, this.itemStacks);
        }
  
     }
  
     public CompoundNBT saveToTag(CompoundNBT nbt) {
        if (!this.trySaveLootTable(nbt)) {
           ItemStackHelper.saveAllItems(nbt, this.itemStacks, true);
        }
        
        return nbt;
     }

    @Override
    public int getContainerSize() {
        return itemStacks.size();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return itemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        itemStacks = items;        
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.jademc.package");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory pInv) {
        return new ChestContainer(ContainerType.GENERIC_3x3, id, pInv, this, 1);
    }

    @Override
    public int[] getSlotsForFace(Direction face) {
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack items, Direction face) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack items, Direction face) {
        return true;
    }

    @Override
    public boolean canOpen(PlayerEntity player) {
        return true;
    }
}
