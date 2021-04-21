package net.dc.jademc.item.tool;

import net.dc.jademc.Archive;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class EnderiteItemTier implements IItemTier {
    static final int level = 8;
    static final int uses = 50000;
    static final float speed = 18.0F;
    static final float damageBonus = 8.0F;
    static final int enchantmentValue = 30;

    // IItemTier
    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damageBonus;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Archive.NoRegistry.ENDERITE_REPAIR_ITEMS);
    }
}
