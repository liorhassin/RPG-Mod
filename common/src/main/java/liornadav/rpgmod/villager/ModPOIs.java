package liornadav.rpgmod.villager;

import com.google.common.collect.ImmutableSet;
import liornadav.rpgmod.block.ModBlocks;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {
    public static final DeferredRegister<PointOfInterestType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, RPGMod.MOD_ID);

    public static final RegistryObject<PointOfInterestType> KAUPEN_PORTAL =
            POI.register("kaupen_portal", () -> new PointOfInterestType(
                    ImmutableSet.copyOf(ModBlocks.KAUPEN_PORTAL.get().getStateManager().getStates())
                    , 1, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
