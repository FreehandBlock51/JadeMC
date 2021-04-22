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
import net.minecraft.item.Rarity;

public class RingOfFlightItem extends Item {

    static final Item.Properties PROPERTIES = new Item.Properties()
        .defaultDurability(600)
        .fireResistant()
        .tab(ItemGroup.TAB_SEARCH)
        .rarity(Rarity.RARE);

        
    public RingOfFlightItem() {
        super(PROPERTIES);
        
        this.setRegistryName("ring_of_flight");
    }

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