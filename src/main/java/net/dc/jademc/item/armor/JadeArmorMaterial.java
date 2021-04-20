package net.dc.jademc.item.armor;

import net.dc.jademc.Archive;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class JadeArmorMaterial implements IArmorMaterial {
    @Override
    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return 4690;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return 250;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public String getName() {
        return "Jade";
    }

    @Override
    public float getToughness() {
        return 250;
    }

    @Override
    public float getKnockbackResistance() {
        return 250;
    }

    @Override
    public int getEnchantmentValue() {
        return 120;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Archive.JADE.getDefaultInstance());
    }
}