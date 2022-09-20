package net.LiorNadav.rpgmod.event.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.ZombieRPGEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ZombieRPGRenderer extends GeoEntityRenderer<ZombieRPGEntity> {
    public ZombieRPGRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ZombieRPGModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieRPGEntity instance) {
        return new ResourceLocation(RPGMod.MOD_ID, "textures/entity/zombie_rpg_texture.png");
    }

    @Override
    public RenderType getRenderType(ZombieRPGEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(3f, 3f, 3f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
