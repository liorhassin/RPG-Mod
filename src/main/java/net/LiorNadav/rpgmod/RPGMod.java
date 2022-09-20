package net.LiorNadav.rpgmod;

import com.mojang.logging.LogUtils;
import net.LiorNadav.rpgmod.block.ModBlocks;
import net.LiorNadav.rpgmod.block.entity.ModBlockEntities;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.fluid.ModFluidTypes;
import net.LiorNadav.rpgmod.fluid.ModFluids;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.recipe.ModRecipes;
import net.LiorNadav.rpgmod.screen.ModMenuTypes;
import net.LiorNadav.rpgmod.villager.ModPOIs;
import net.LiorNadav.rpgmod.villager.ModVillagers;
import net.LiorNadav.rpgmod.world.biomemods.ModBiomeModifiers;
import net.LiorNadav.rpgmod.world.dimension.ModDimensions;
import net.LiorNadav.rpgmod.world.feature.ModConfiguredFeatures;
import net.LiorNadav.rpgmod.world.feature.ModPlacedFeatures;
import net.LiorNadav.rpgmod.world.structure.ModStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPGMod.MOD_ID)
public class RPGMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "rpgmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public RPGMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
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
        MinecraftForge.EVENT_BUS.register(this);
        ModStructures.register(modEventBus);
        ModBiomeModifiers.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
        });
        ModMessages.register();
    }
}
