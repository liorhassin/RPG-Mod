package net.LiorNadav.rpgmod.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.RedOgreEntity;
import net.LiorNadav.rpgmod.world.entity.projectile.TorchArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RPGMod.MOD_ID);

    /*
    public static final RegistryObject<EntityType<TorchArrow>> TORCH_ARROW = ENTITY_TYPES.register("torch_arrow",
            ()-> EntityType.Builder.<TorchArrow>of(TorchArrow::new, MobCategory.MISC).sized(0.5F,0.5F)
                    .clientTrackingRange(4).updateInterval(20)
                    .build(new ResourceLocation(RPGMod.MOD_ID, "torch_arrow").toString()));
     */

    public static final RegistryObject<EntityType<TorchArrow>> TORCH_ARROW = ENTITY_TYPES.register("explosive_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<TorchArrow>) TorchArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("explosive_arrow"));

    public static final RegistryObject<EntityType<RedOgreEntity>> RED_OGRE =
            ENTITY_TYPES.register("red_ogre",
                    () -> EntityType.Builder.of(RedOgreEntity::new, MobCategory.MONSTER)
                            .sized(2.3f, 4.5f)
                            .build(new ResourceLocation(RPGMod.MOD_ID, "red_ogre").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

