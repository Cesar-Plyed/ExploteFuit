package dev.cesarplyed.explotefruit.datagen;

import dev.cesarplyed.explotefruit.block.ModBlocks;
import dev.cesarplyed.explotefruit.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnLangProvider extends FabricLanguageProvider {
    public ModEnLangProvider(FabricDataOutput dataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @SuppressWarnings("null")
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup,
            TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.EXPLOSIVE_BERRY, "Explosive Berry");
        translationBuilder.add(ModItems.FIRE_BERRY, "Fire Berry");
        translationBuilder.add(ModItems.THUNDER_BERRY, "Thunder Berry");
        translationBuilder.add(ModItems.BERRY_CROSSBOW, "Berry Crossbow");
        translationBuilder.add(ModItems.RANDOM_BERRY, "Random Berry");

        translationBuilder.add(ModBlocks.EXPLOSIVE_BERRY_BLOCK, "Explosive Berry Bush");
        translationBuilder.add(ModBlocks.FIRE_BERRY_BLOCK, "Fire Berry Bush");
        translationBuilder.add(ModBlocks.THUNDER_BERRY_BLOCK, "Thunder Berry Bush");
        translationBuilder.add(ModBlocks.RANDOM_BERRY_BUSH, "Random Berry Bush");
    }
}
