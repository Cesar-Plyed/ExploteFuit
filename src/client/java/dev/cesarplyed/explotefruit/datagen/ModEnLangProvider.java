package dev.cesarplyed.explotefruit.datagen;

import dev.cesarplyed.explotefruit.item.ModItems;
import dev.cesarplyed.explotefruit.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * Proveedor de idioma (Traducciones) para generar el archivo en_us.json.
 */
public class ModEnLangProvider extends FabricLanguageProvider {

    public ModEnLangProvider(FabricDataOutput dataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup,
            TranslationBuilder translationBuilder) {

        // Ítems
        translationBuilder.add(ModItems.EXPLOSIVE_BERRY, "Explosive Berry");
        translationBuilder.add(ModItems.FIRE_BERRY, "Fire Berry");
        translationBuilder.add(ModItems.THUNDER_BERRY, "Thunder Berry");

        // Bloques
        translationBuilder.add(ModBlocks.EXPLOSIVE_BERRY_BLOCK, "Explosive Berry Block");
        translationBuilder.add(ModBlocks.FIRE_BERRY_BLOCK, "Fire Berry Block");
        translationBuilder.add(ModBlocks.THUNDER_BERRY_BLOCK, "Thunder Berry Block");

        // Grupo Creativo
        translationBuilder.add("itemGroup.explotefruit", "Explosive Fruits");
    }
}
