package net.LiorNadav.rpgmod.client.renderer.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.world.entity.projectile.IronArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronArrowRenderer extends ArrowRenderer<IronArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RPGMod.MOD_ID, "textures/entity/ammunition/iron_arrow.png");
    public IronArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(IronArrowEntity arrow) {
        return TEXTURE;
    }
}
