package dev.cesarplyed.explotefruit.datagen;

import dev.cesarplyed.explotefruit.block.ModBlocks;
import dev.cesarplyed.explotefruit.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

/**
 * Proveedor de Recetas para el DataGen.
 */
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                // Receta: 9 Frutas -> 1 Bloque (y viceversa con offerReversible...)

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.EXPLOSIVE_BERRY,
                        RecipeCategory.DECORATIONS, ModBlocks.EXPLOSIVE_BERRY_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.FIRE_BERRY,
                        RecipeCategory.DECORATIONS, ModBlocks.FIRE_BERRY_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.THUNDER_BERRY,
                        RecipeCategory.DECORATIONS, ModBlocks.THUNDER_BERRY_BLOCK);
            }
        };
    }

    @Override
    public String getName() {
        return "ExploteFruit Recipes";
    }
}
