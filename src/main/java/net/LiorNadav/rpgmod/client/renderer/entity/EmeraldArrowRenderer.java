package net.LiorNadav.rpgmod.client.renderer.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.world.entity.projectile.EmeraldArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EmeraldArrowRenderer extends ArrowRenderer<EmeraldArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RPGMod.MOD_ID, "textures/entity/ammunition/emerald_arrow.png");
    public EmeraldArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public ResourceLocation getTextureLocation(EmeraldArrowEntity arrow) {
        return TEXTURE;
    }
}
