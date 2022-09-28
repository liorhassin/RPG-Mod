package liornadav.rpgmod.event.client;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.RedOgreEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RedOgreModel extends AnimatedGeoModel<RedOgreEntity> {
    @Override
    public ResourceLocation getModelResource(RedOgreEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "geo/red_ogre.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RedOgreEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "textures/entity/red_ogre_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RedOgreEntity animatable) {
        return new ResourceLocation(RPGMod.MOD_ID, "animations/red_ogre.animation.json");
    }
}
