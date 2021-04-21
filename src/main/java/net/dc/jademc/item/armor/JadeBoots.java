package net.dc.jademc.item.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class JadeBoots extends ArmorItem {

    public JadeBoots() {
        super(new JadeArmorMaterial(), EquipmentSlotType.HEAD, JadeArmorMaterial.PROPERTIES);
        
        this.setRegistryName("jade_boots");
    }
}
