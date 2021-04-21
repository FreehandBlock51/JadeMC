package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class EnderiteHelmet extends ArmorItem {
    public EnderiteHelmet() {
        super(new EnderiteArmorMaterial(), EquipmentSlotType.HEAD, EnderiteArmorMaterial.PROPERTIES);

        this.setRegistryName("enderite_helmet");
    }
}
