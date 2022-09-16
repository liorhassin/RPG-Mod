package net.LiorNadav.rpgmod.client.renderer.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.world.entity.projectile.TorchArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TorchArrowRenderer extends ArrowRenderer<TorchArrow> {
    public TorchArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(TorchArrow arrow) {
        return new ResourceLocation(RPGMod.MOD_ID, "textures/entity/ammunition/torch_arrow.png");
    }
}
