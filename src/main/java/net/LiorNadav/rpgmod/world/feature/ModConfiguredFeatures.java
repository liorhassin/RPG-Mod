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

    //"STONE_ORE_REPLACEABLES" meaning "BLUE_ORE" will only be able to replace Stone blocks
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_BLUE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BLUE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.BLUE_ORE.get().defaultBlockState())));
            //If we want ore to spawn in special type of block
            //OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.BLUE_ORE.get().defaultBlockState()

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_RED_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RED_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.RED_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_ORE = CONFIGURED_FEATURES.register("blue_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_BLUE_ORES.get(),4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_ORE = CONFIGURED_FEATURES.register("red_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_RED_ORES.get(), 4)));

    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
    }
}
