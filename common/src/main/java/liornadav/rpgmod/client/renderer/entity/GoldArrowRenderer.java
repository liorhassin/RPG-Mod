package liornadav.rpgmod.client.renderer.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.world.entity.projectile.GoldArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldArrowRenderer extends ArrowRenderer<GoldArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RPGMod.MOD_ID, "textures/entity/ammunition/gold_arrow.png");
    public GoldArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public ResourceLocation getTextureLocation(GoldArrowEntity arrow) {
        return TEXTURE;
    }
}
