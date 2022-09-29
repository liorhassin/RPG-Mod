package liornadav.rpgmod.world.biomemods;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.world.feature.ModEntityBiomeModifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, RPGMod.MOD_ID);

    public static RegistryObject<Codec<ModEntityBiomeModifier>> ENTITY_MODIFIER = BIOME_MODIFIERS.register("entities", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.REGISTRY_ENTRY_LIST_CODEC.fieldOf("biomes").forGetter(ModEntityBiomeModifier::biomes),
                    SpawnSettings.SpawnEntry.CODEC.fieldOf("entity").forGetter(ModEntityBiomeModifier::spawnerData)
            ).apply(builder, ModEntityBiomeModifier::new)));


    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }
}
