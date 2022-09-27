package liornadav.rpgmod.fabric;

import liornadav.rpgmod.RPGMod;
import net.fabricmc.api.ModInitializer;

public class RPGModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RPGMod.init();
    }
}