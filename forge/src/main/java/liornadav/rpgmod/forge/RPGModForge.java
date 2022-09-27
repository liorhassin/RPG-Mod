package liornadav.rpgmod.forge;

import dev.architectury.platform.forge.EventBuses;
import liornadav.rpgmod.RPGMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RPGMod.MOD_ID)
public class RPGModForge {
    public RPGModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(RPGMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RPGMod.init();
    }
}