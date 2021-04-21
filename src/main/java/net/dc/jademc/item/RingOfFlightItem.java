package net.dc.jademc.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RingOfFlightItem extends Item {

    static final Item.Properties PROPERTIES = new Item.Properties()
        .defaultDurability(600)
        .fireResistant()
        .tab(ItemGroup.TAB_SEARCH);

        
    public RingOfFlightItem() {
        super(PROPERTIES);
        
        this.setRegistryName("ring_of_flight");
    }
        
    /*
    private boolean gFly = false;
    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        gFly = true;
        player.abilities.mayfly = gFly;
    }
    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        if (!player.isCreative() && !player.isSpectator()) {
            player.abilities.mayfly = false;
            player.abilities.flying = false;
        }
        this.gFly = false;
        return super.onDroppedByPlayer(item, player);
    }
    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int slot,
            boolean b) {
        if (entity instanceof PlayerEntity) {
            if (((PlayerEntity) entity).isCreative() || ((PlayerEntity) entity).isSpectator()) {
                return;
            }
            if (this.gFly) {
                boolean flying = false;
                for (ItemStack stack : ((PlayerEntity) entity).getArmorSlots()) {
                    flying = flying || stack.equals(stack, true);
                }
                this.gFly = flying;
            }
            if (!this.gFly) {
                ((PlayerEntity) entity).abilities.mayfly = gFly;
                ((PlayerEntity) entity).abilities.flying = gFly;
            }
        }
    }
    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.CHEST;
    }*/

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int currentDamage = stack.getDamageValue();
        if (++currentDamage < stack.getMaxDamage() && !player.hasEffect(Effects.LEVITATION)) {
            player.addEffect(new EffectInstance(Effects.LEVITATION, 10, 5));
            stack.setDamageValue(currentDamage);
        }
        return super.use(world, player, hand);
    }
    @Override
    public float getXpRepairRatio(ItemStack stack) {
        return 0.01F;
    }
}