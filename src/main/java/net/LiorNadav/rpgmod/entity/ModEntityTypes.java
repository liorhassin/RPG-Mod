package net.LiorNadav.rpgmod.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.custom.RedOgreEntity;
import net.LiorNadav.rpgmod.entity.custom.ZombieRPGEntity;
import net.LiorNadav.rpgmod.world.entity.projectile.*;
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

    //---------------------Arrow Entity----------------------//
    public static final RegistryObject<EntityType<TorchArrowEntity>> TORCH_ARROW = ENTITY_TYPES.register("torch_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<TorchArrowEntity>) TorchArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("torch_arrow"));

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW = ENTITY_TYPES.register("explosive_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<ExplosiveArrowEntity>) ExplosiveArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("explosive_arrow"));

    public static final RegistryObject<EntityType<FrostArrowEntity>> FROST_ARROW = ENTITY_TYPES.register("frost_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<FrostArrowEntity>) FrostArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("frost_arrow"));

    public static final RegistryObject<EntityType<IronArrowEntity>> IRON_ARROW = ENTITY_TYPES.register("iron_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<IronArrowEntity>) IronArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("iron_arrow"));

    public static final RegistryObject<EntityType<GoldArrowEntity>> GOLD_ARROW = ENTITY_TYPES.register("gold_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<GoldArrowEntity>) GoldArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("gold_arrow"));

    public static final RegistryObject<EntityType<DiamondArrowEntity>> DIAMOND_ARROW = ENTITY_TYPES.register("diamond_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<DiamondArrowEntity>) DiamondArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("diamond_arrow"));

    public static final RegistryObject<EntityType<EmeraldArrowEntity>> EMERALD_ARROW = ENTITY_TYPES.register("emerald_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<EmeraldArrowEntity>) EmeraldArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4)
                    .updateInterval(20).build("emerald_arrow"));

    //-----------------------Monster Entity--------------------------//
    public static final RegistryObject<EntityType<RedOgreEntity>> RED_OGRE =
            ENTITY_TYPES.register("red_ogre",
                    () -> EntityType.Builder.of(RedOgreEntity::new, MobCategory.MONSTER)
                            .sized(2.3f, 4.5f)
                            .build(new ResourceLocation(RPGMod.MOD_ID, "red_ogre").toString()));

    public static final RegistryObject<EntityType<ZombieRPGEntity>> ZOMBIE_RPG =
            ENTITY_TYPES.register("zombie_rpg",
                    () -> EntityType.Builder.of(ZombieRPGEntity::new, MobCategory.MONSTER)
                            .sized(1, 1.8f)
                            .build(new ResourceLocation(RPGMod.MOD_ID, "zombie_rpg").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

