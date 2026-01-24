package dev.cesarplyed.explotefruit.datagen;

import dev.cesarplyed.explotefruit.block.ModBlocks;
import dev.cesarplyed.explotefruit.block.custom.BerryBlock;
import dev.cesarplyed.explotefruit.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

/**
 * Proveedor de modelos para asignar texturas a los bloques y objetos.
 */
public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Bloque sólido
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RANDOM_BERRY_BUSH);

        // Registro de Arbustos (Explosive Berry con etapas completas)
        blockStateModelGenerator.registerCrop(ModBlocks.EXPLOSIVE_BERRY_BLOCK, BerryBlock.AGE, 0, 1, 2, 3);

        // Para Fire y Thunder, como aún no tienen todas las texturas,
        // usaremos registerSimpleCubeAll para evitar que Datagen falle al buscar
        // archivos inexistentes.
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FIRE_BERRY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.THUNDER_BERRY_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.EXPLOSIVE_BERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_BERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.THUNDER_BERRY, Models.GENERATED);
    }
}
