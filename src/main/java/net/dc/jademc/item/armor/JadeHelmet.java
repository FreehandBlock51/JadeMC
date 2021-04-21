package net.dc.jademc.item.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class JadeHelmet extends ArmorItem {
    static final Properties PROPERTIES = new Properties()
        .tab(ItemGroup.TAB_COMBAT)
        .fireResistant();

    public JadeHelmet() {
        super(new JadeArmorMaterial(), EquipmentSlotType.HEAD, PROPERTIES);
        
        this.setRegistryName("jade_helmet");
    }
    
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
            EquipmentSlotType armorSlot, A _default) {
        return _default;
    }
}
