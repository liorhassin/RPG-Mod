package liornadav.rpgmod.recipe;

import net.LiorNadav.rpgmod.RPGMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RPGMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PurifierRecipe>> PURIFIER_SERIALIZER =
            SERIALIZERS.register("purifying", () -> PurifierRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
