package liornadav.rpgmod.client;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.client.renderer.entity.*;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.event.client.BlueOgreRenderer;
import net.LiorNadav.rpgmod.event.client.GreenOgreRenderer;
import net.LiorNadav.rpgmod.event.client.RedOgreRenderer;
import net.LiorNadav.rpgmod.event.client.ZombieRPGRenderer;
import net.LiorNadav.rpgmod.fluid.ModFluids;
import net.LiorNadav.rpgmod.screen.ModMenuTypes;
import net.LiorNadav.rpgmod.screen.PurifierScreen;
import net.LiorNadav.rpgmod.util.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RPGMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.TORCH_ARROW.get(), TorchArrowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FROST_ARROW.get(), FrostArrowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.IRON_ARROW.get(), IronArrowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GOLD_ARROW.get(), GoldArrowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.DIAMOND_ARROW.get(), DiamondArrowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.EMERALD_ARROW.get(), EmeraldArrowRenderer::new);
        ModItemProperties.addCustomItemProperties();
        MenuScreens.register(ModMenuTypes.PURIFIER_MENU.get(), PurifierScreen::new);
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());
        EntityRenderers.register(ModEntityTypes.RED_OGRE.get(), RedOgreRenderer::new);
        EntityRenderers.register(ModEntityTypes.GREEN_OGRE.get(), GreenOgreRenderer::new);
        EntityRenderers.register(ModEntityTypes.BLUE_OGRE.get(), BlueOgreRenderer::new);
        EntityRenderers.register(ModEntityTypes.ZOMBIE_RPG.get(), ZombieRPGRenderer::new);
    }
}
