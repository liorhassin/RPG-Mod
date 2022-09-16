package net.LiorNadav.rpgmod;

import com.mojang.logging.LogUtils;
import net.LiorNadav.rpgmod.block.ModBlocks;
import net.LiorNadav.rpgmod.block.entity.ModBlockEntities;
import net.LiorNadav.rpgmod.fluid.ModFluidTypes;
import net.LiorNadav.rpgmod.fluid.ModFluids;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.recipe.ModRecipes;
import net.LiorNadav.rpgmod.screen.ModMenuTypes;
import net.LiorNadav.rpgmod.screen.PurifierScreen;
import net.LiorNadav.rpgmod.util.ModItemProperties;
import net.LiorNadav.rpgmod.villager.ModPOIs;
import net.LiorNadav.rpgmod.villager.ModVillagers;
import net.LiorNadav.rpgmod.world.dimension.ModDimensions;
import net.LiorNadav.rpgmod.world.entity.ModEntityType;
import net.LiorNadav.rpgmod.world.feature.ModConfiguredFeatures;
import net.LiorNadav.rpgmod.world.feature.ModPlacedFeatures;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

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
        ModPlacedFeatures.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        ModDimensions.register();
        ModPOIs.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        ModEntityType.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
        });
        ModMessages.register();
        ModItemProperties.addCustomItemProperties();
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            ModItemProperties.addCustomItemProperties();
            MenuScreens.register(ModMenuTypes.PURIFIER_MENU.get(), PurifierScreen::new);
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());
        }
    }
}
