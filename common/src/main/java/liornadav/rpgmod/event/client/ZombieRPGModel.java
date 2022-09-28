package liornadav.rpgmod.event.client;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.ZombieRPGEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZombieRPGModel extends AnimatedGeoModel<ZombieRPGEntity> {
    @Override
    public ResourceLocation getModelResource(ZombieRPGEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "geo/zombie_rpg.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ZombieRPGEntity object) {
        return new ResourceLocation(RPGMod.MOD_ID, "textures/entity/zombie_rpg_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ZombieRPGEntity animatable) {
        return new ResourceLocation(RPGMod.MOD_ID, "animations/zombie_rpg.animation.json");
    }
}
