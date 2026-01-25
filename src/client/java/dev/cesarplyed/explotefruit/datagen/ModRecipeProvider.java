package dev.cesarplyed.explotefruit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
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

    @SuppressWarnings("null")
    @Override
    public RecipeGenerator getRecipeGenerator(@SuppressWarnings("null") RegistryWrapper.WrapperLookup registryLookup, @SuppressWarnings("null") RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                
            }
        };
    }

    @Override
    public String getName() {
        return "ExploteFruit Recipes";
    }
}