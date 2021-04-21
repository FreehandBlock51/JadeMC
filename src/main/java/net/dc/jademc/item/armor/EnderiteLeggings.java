package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class EnderiteLeggings extends ArmorItem {
    public EnderiteLeggings() {
        super(new EnderiteArmorMaterial(), EquipmentSlotType.LEGS, EnderiteArmorMaterial.PROPERTIES);

        this.setRegistryName("enderite_leggings");
    }
}
