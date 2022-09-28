package liornadav.rpgmod;

import com.mojang.logging.LogUtils;
import liornadav.rpgmod.block.ModBlocks;
import liornadav.rpgmod.block.entity.ModBlockEntities;
import liornadav.rpgmod.entity.ModEntityTypes;
import liornadav.rpgmod.fluid.ModFluidTypes;
import liornadav.rpgmod.fluid.ModFluids;
import liornadav.rpgmod.item.ModItems;
import liornadav.rpgmod.recipe.ModRecipes;
import liornadav.rpgmod.screen.ModMenuTypes;
import liornadav.rpgmod.villager.ModPOIs;
import liornadav.rpgmod.villager.ModVillagers;
import liornadav.rpgmod.world.biomemods.ModBiomeModifiers;
import liornadav.rpgmod.world.dimension.ModDimensions;
import liornadav.rpgmod.world.feature.ModConfiguredFeatures;
import liornadav.rpgmod.world.feature.ModPlacedFeatures;
import liornadav.rpgmod.world.structure.ModStructures;
import org.slf4j.Logger;

public class RPGMod {
    public static final String MOD_ID = "rpgmod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        ModItems.ITEMS.register();
        ModBlocks.BLOCKS.register();
        ModConfiguredFeatures.register(modEventBus);
        ModDimensions.register();
        ModPOIs.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        GeckoLib.initialize();
        ModStructures.register(modEventBus);
        ModBiomeModifiers.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
    }
}