package dev.cesarplyed.explotefruit;

import dev.cesarplyed.explotefruit.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * Punto de entrada para la generación de datos (Data Generation).
 * Define qué proveedores se ejecutarán.
 */
public class ExploteFruitDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Añadimos el proveedor de modelos.
        pack.addProvider(ModModelProvider::new);

        // Añadimos el proveedor de traducciones (Idioma).
        pack.addProvider(dev.cesarplyed.explotefruit.datagen.ModEnLangProvider::new);

        // Añadimos el proveedor de recetas.
        pack.addProvider(dev.cesarplyed.explotefruit.datagen.ModRecipeProvider::new);
    }
}
