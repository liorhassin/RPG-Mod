package liornadav.rpgmod.intergration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.recipe.PurifierRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIRPGModPlugin implements IModPlugin {

    public static RecipeType<PurifierRecipe> PURIFYING_TYPE =
            new RecipeType<>(PurifierRecipeCategory.UID, PurifierRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RPGMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                PurifierRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<PurifierRecipe> recipesInfusing = rm.getAllRecipesFor(PurifierRecipe.Type.INSTANCE);
        registration.addRecipes(PURIFYING_TYPE, recipesInfusing);
    }
}
