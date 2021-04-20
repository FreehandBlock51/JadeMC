package net.dc.jademc.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RingOfFlightItem extends Item {

    static final Item.Properties PROPERTIES = new Item.Properties()
        .defaultDurability(600)
        .fireResistant()
        .tab(ItemGroup.TAB_SEARCH);

    boolean gFly = false;
    static final boolean x = true;

    public RingOfFlightItem() {
        super(PROPERTIES);

        this.setRegistryName("ring_of_flight");
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        gFly = true;
        player.abilities.mayfly = gFly;
    }
    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        if (x) { return false; }
        if (!player.isCreative() || !player.isSpectator()) {
            player.abilities.mayfly = false;
            player.abilities.flying = false;
        }
        this.gFly = false;
        return super.onDroppedByPlayer(item, player);
    }
    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_,
            boolean p_77663_5_) {
        if (p_77663_3_ instanceof PlayerEntity) {
            if (((PlayerEntity) p_77663_3_).isCreative() || ((PlayerEntity) p_77663_3_).isSpectator()) {
                return;
            }
            if (this.gFly) {
                boolean flying = false;
                for (ItemStack stack : ((PlayerEntity) p_77663_3_).getArmorSlots()) {
                    flying = flying || stack.equals(p_77663_1_, true);
                }
                this.gFly = flying;
            }
            if (!this.gFly) {
                ((PlayerEntity) p_77663_3_).abilities.mayfly = gFly;
                ((PlayerEntity) p_77663_3_).abilities.flying = gFly;
            }
        }
    }
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.CHEST;
    }
}