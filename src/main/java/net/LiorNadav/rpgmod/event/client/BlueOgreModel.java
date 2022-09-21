package net.LiorNadav.rpgmod.event.client;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.BlueOgreEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlueOgreModel extends AnimatedGeoModel<BlueOgreEntity> {
    @Override
    public ResourceLocation getModelResource(BlueOgreEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "geo/blue_ogre.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlueOgreEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "textures/entity/blue_ogre_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlueOgreEntity animatable) {
        return new ResourceLocation(RPGMod.MOD_ID, "animations/blue_ogre.animation.json");
    }
}
