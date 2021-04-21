package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class EnderiteBoots extends ArmorItem {
    public EnderiteBoots() {
        super(new EnderiteArmorMaterial(), EquipmentSlotType.FEET, EnderiteArmorMaterial.PROPERTIES);

        this.setRegistryName("enderite_boots");
    }
}
