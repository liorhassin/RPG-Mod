package net.LiorNadav.rpgmod.world.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.world.entity.projectile.TorchArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RPGMod.MOD_ID);

    //public static final EntityType<Arrow> ARROW = register("arrow", EntityType.Builder.<Arrow>of(Arrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));

    public static final RegistryObject<EntityType<TorchArrow>> TORCH_ARROW = ENTITIES.register("torch_arrow",
            ()-> EntityType.Builder.<TorchArrow>of(TorchArrow::new, MobCategory.MISC).sized(0.5F,0.5F)
                    .clientTrackingRange(4).updateInterval(20)
                    .build(new ResourceLocation(RPGMod.MOD_ID, "torch_arrow").toString()));

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
