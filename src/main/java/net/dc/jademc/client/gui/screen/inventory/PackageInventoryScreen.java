package net.dc.jademc.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.dc.jademc.inventory.container.PackageContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

@Deprecated
public class PackageInventoryScreen extends ContainerScreen<PackageContainer> {
    static final TranslationTextComponent TITLE = new TranslationTextComponent("block.jademc.package");

    public PackageInventoryScreen(PackageContainer container, PlayerEntity player) {
        super(container, player.inventory, TITLE);
        ++this.imageHeight;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float unusedFloat) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, unusedFloat);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack stack, float f, int i0, int i1) {
        super.renderBackground(stack);
    }
}
