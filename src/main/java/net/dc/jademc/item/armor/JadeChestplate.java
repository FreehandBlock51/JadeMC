package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class JadeChestplate extends ArmorItem {

    public JadeChestplate() {
        super(new JadeArmorMaterial(), EquipmentSlotType.CHEST, JadeArmorMaterial.PROPERTIES);
        
        this.setRegistryName("jade_chestplate");
    }
}
