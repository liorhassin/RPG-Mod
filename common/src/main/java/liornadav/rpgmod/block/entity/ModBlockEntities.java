package liornadav.rpgmod.block.entity;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RPGMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<PurifierBlockEntity>> PURIFIER =
            BLOCK_ENTITIES.register("purifier", () ->
                    BlockEntityType.Builder.of(PurifierBlockEntity::new,
                    ModBlocks.PURIFIER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
