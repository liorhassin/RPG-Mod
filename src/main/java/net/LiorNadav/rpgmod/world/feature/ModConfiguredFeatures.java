package net.LiorNadav.rpgmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, RPGMod.MOD_ID);

    //Ore spawn zones:
    //----------------
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_AZURITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.AZURITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.AZURITE_ORE.get().defaultBlockState())));
            //If we want ore to spawn in special type of block
            //OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.BLUE_ORE.get().defaultBlockState()

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ADAMANTITE_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ADAMANTITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.ADAMANTITE_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_JADEITE_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.JADEITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.JADEITE_ORE.get().defaultBlockState())));


    //Ore registry:
    //-------------
    public static final RegistryObject<ConfiguredFeature<?, ?>> AZURITE_ORE = CONFIGURED_FEATURES.register("azurite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_AZURITE_ORES.get(),4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ADAMANTITE_ORE = CONFIGURED_FEATURES.register("adamantite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ADAMANTITE_ORES.get(), 4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> JADEITE_ORE = CONFIGURED_FEATURES.register("jadeite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_JADEITE_ORES.get(), 4)));
    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
    }
}
