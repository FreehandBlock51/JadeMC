package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class EnderiteChestplate extends ArmorItem {
    public EnderiteChestplate() {
        super(new EnderiteArmorMaterial(), EquipmentSlotType.CHEST, EnderiteArmorMaterial.PROPERTIES);

        this.setRegistryName("enderite_chestplate");
    }
}
