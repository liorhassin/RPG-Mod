package net.LiorNadav.rpgmod.world.feature;

import net.LiorNadav.rpgmod.RPGMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, RPGMod.MOD_ID);

    //Setting place feature of ore blocks:
    //------------------------------------
    public static final RegistryObject<PlacedFeature> AZURITE_ORE_PLACED = PLACED_FEATURES.register("azurite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.AZURITE_ORE.getHolder().get(),
                    commonOrePlacement(12, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(200)))));

    public static final RegistryObject<PlacedFeature> ADAMANTITE_ORE_PLACED = PLACED_FEATURES.register("adamantite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ADAMANTITE_ORE.getHolder().get(),
                    commonOrePlacement(12, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(200)))));

    public static final RegistryObject<PlacedFeature> JADEITE_ORE_PLACED = PLACED_FEATURES.register("jadeite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.JADEITE_ORE.getHolder().get(),
                    commonOrePlacement(12, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(200)))));


    //Taken from minecraft OrePlacement File , 3 private methods changed to public.
    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register (IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
