package net.dc.jademc.item.armor;

import net.dc.jademc.Archive;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class JadeArmorMaterial implements IArmorMaterial {
    static final String name = "jade";
    static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    static final int durabilityMultiplier = 30;
    static final int[] slotProtections = new int[]{4, 10, 12, 4};
    static final int enchantmentValue = 18;
    static final SoundEvent sound = SoundEvents.ARMOR_EQUIP_GENERIC;
    static final float toughness = 1.5F;
    static final float knockbackResistance = 0.2F;

    @Override
    public int getDurabilityForSlot(EquipmentSlotType type) {
        return HEALTH_PER_SLOT[type.getIndex()] * durabilityMultiplier;
     }
  
     @Override
     public int getDefenseForSlot(EquipmentSlotType type) {
        return slotProtections[type.getIndex()];
     }
  
     @Override
     public int getEnchantmentValue() {
        return enchantmentValue;
     }
  
     @Override
     public SoundEvent getEquipSound() {
        return sound;
     }
  
     @Override
     public Ingredient getRepairIngredient() {
        return Ingredient.of(Archive.JADE.getDefaultInstance());
     }
  
     @Override
     @OnlyIn(Dist.CLIENT)
     public String getName() {
        return name;
     }
  
     @Override
     public float getToughness() {
        return toughness;
     }
  
     @Override
     public float getKnockbackResistance() {
        return knockbackResistance;
     }
}