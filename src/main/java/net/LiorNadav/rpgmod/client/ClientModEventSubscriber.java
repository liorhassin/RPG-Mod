package net.LiorNadav.rpgmod.client;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.client.renderer.entity.TorchArrowRenderer;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RPGMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.TORCH_ARROW.get(), TorchArrowRenderer::new);
    }
}
