package liornadav.rpgmod.villager;

import com.google.common.collect.ImmutableSet;
import liornadav.rpgmod.block.ModBlocks;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.block.ModBlocks;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, RPGMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, RPGMod.MOD_ID);

    //-------------------- Custom POI types -------------------------------//

    public static final RegistryObject<PointOfInterestType> VILLAGER_BLOCK_POI = POI_TYPES.register("villager_block_poi",
            () -> new PointOfInterestType(ImmutableSet.copyOf(ModBlocks.VILLAGER_BLOCK.get().getStateManager().getStates()),
                    1, 12));

    //----------------------- Custom Villagers ---------------------------//

    public static final RegistryObject<VillagerProfession> DARK_MAGE = VILLAGER_PROFESSIONS.register("dark_mage",
            () -> new VillagerProfession("dark_mage", x -> x.get() == VILLAGER_BLOCK_POI.get(),
                    x -> x.get() == VILLAGER_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.ENTITY_VILLAGER_WORK_ARMORER));


    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class,
                    "registerBlockStates", PointOfInterestType.class).invoke(null, VILLAGER_BLOCK_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
