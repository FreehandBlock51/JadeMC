package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class JadeHelmet extends ArmorItem {

    public JadeHelmet() {
        super(new JadeArmorMaterial(), EquipmentSlotType.HEAD, JadeArmorMaterial.PROPERTIES);
        
        this.setRegistryName("jade_helmet");
    }
}
