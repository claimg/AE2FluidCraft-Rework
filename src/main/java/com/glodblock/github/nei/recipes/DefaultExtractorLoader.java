package com.glodblock.github.nei.recipes;

import com.glodblock.github.nei.recipes.extractor.EnderIORecipeExtractor;
import com.glodblock.github.nei.recipes.extractor.GregTech5RecipeExtractor;
import com.glodblock.github.nei.recipes.extractor.GregTech6RecipeExtractor;
import com.glodblock.github.nei.recipes.extractor.VanillaRecipeExtractor;
import com.glodblock.github.util.ModAndClassUtil;
import gregapi.recipes.Recipe;
import gregtech.api.util.GT_Recipe;

public class DefaultExtractorLoader implements Runnable {

    @Override
    public void run() {
        FluidRecipe.addRecipeMap("smelting", new VanillaRecipeExtractor(false));
        FluidRecipe.addRecipeMap("brewing", new VanillaRecipeExtractor(false));
        FluidRecipe.addRecipeMap("crafting", new VanillaRecipeExtractor(true));
        FluidRecipe.addRecipeMap("crafting2x2", new VanillaRecipeExtractor(true));

        if (ModAndClassUtil.GT5) {
            for (GT_Recipe.GT_Recipe_Map tMap : GT_Recipe.GT_Recipe_Map.sMappings) {
                FluidRecipe.addRecipeMap(tMap.mNEIName, new GregTech5RecipeExtractor(tMap.mNEIName.equals("gt.recipe.scanner") || tMap.mNEIName.equals("gt.recipe.fakeAssemblylineProcess")));
            }
        }

        if (ModAndClassUtil.GT6) {
            for (Recipe.RecipeMap tMap : Recipe.RecipeMap.RECIPE_MAPS.values()) {
                FluidRecipe.addRecipeMap(tMap.mNameNEI, new GregTech6RecipeExtractor(tMap));
            }
        }

        if (ModAndClassUtil.EIO) {
            FluidRecipe.addRecipeMap("EIOEnchanter", new EnderIORecipeExtractor());
            FluidRecipe.addRecipeMap("EnderIOAlloySmelter", new EnderIORecipeExtractor());
            FluidRecipe.addRecipeMap("EnderIOSagMill", new EnderIORecipeExtractor());
            FluidRecipe.addRecipeMap("EnderIOSliceAndSplice", new EnderIORecipeExtractor());
            FluidRecipe.addRecipeMap("EnderIOSoulBinder", new EnderIORecipeExtractor());
            FluidRecipe.addRecipeMap("EnderIOVat", new EnderIORecipeExtractor());
        }

    }

}
