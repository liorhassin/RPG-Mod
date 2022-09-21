package net.LiorNadav.rpgmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.LiorNadav.rpgmod.RPGMod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ManaHudOverlay {
    public static final ResourceLocation FILLED_MANA = new ResourceLocation(RPGMod.MOD_ID,
            "textures/mana/filled_mana.png");
    public static final ResourceLocation EMPTY_MANA = new ResourceLocation(RPGMod.MOD_ID,
            "textures/mana/empty_mana.png");

    public static final IGuiOverlay HUD_MANA = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth/2-102;
        int y = screenHeight - 52;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, EMPTY_MANA);
        for(int i = 10; i<=100;i+=10){
            if (ClientManaData.getPlayerMana() <= i) {
                GuiComponent.blit(poseStack, x + (i/10 * 8), y, 0, 0, 15, 15, 15, 15);
            }
        }

        RenderSystem.setShaderTexture(0, FILLED_MANA);
        for(int i = 10; i<=100;i+=10){
            if (ClientManaData.getPlayerMana() >= i){
                GuiComponent.blit(poseStack, x + (i/10 * 8), y, 0, 0, 15, 15, 15, 15);
            }
            else{
                break;
            }
        }
    });
}
