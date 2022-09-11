package net.LiorNadav.rpgmod.event;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid= RPGMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.GUIDE_BOOK.consumeClick()){
                Minecraft.getInstance().player.sendSystemMessage(Component.literal(
                        "Guide book is not implemented yet."));
            }
        }
    }

    @Mod.EventBusSubscriber(modid= RPGMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.GUIDE_BOOK);
        }
    }
}
