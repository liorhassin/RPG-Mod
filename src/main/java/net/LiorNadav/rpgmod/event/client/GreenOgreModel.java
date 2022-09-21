package net.LiorNadav.rpgmod.event.client;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.GreenOgreEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GreenOgreModel extends AnimatedGeoModel<GreenOgreEntity> {
    @Override
    public ResourceLocation getModelResource(GreenOgreEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "geo/green_ogre.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GreenOgreEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "textures/entity/green_ogre_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GreenOgreEntity animatable) {
        return new ResourceLocation(RPGMod.MOD_ID, "animations/green_ogre.animation.json");
    }
}
