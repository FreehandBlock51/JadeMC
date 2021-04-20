package net.dc.jademc.item.tool;

import net.dc.jademc.Archive;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class JadeItemTier implements IItemTier {
    static final int level = 5;
    static final int uses = 25000;
    static final float speed = 12.0F;
    static final float damageBonus = 4.0F;
    static final int enchantmentValue = 28;

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
        return Ingredient.of(Archive.NoRegistry.JADE_REPAIR_ITEMS);
    }
}
