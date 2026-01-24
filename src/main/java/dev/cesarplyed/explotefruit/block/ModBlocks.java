package dev.cesarplyed.explotefruit.block;

import dev.cesarplyed.explotefruit.block.custom.BerryBlock;
import dev.cesarplyed.explotefruit.block.custom.RandomBerryBushBlock;
import dev.cesarplyed.explotefruit.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;

/**
 * Clase para registrar todos los bloques del mod.
 */
public class ModBlocks {

        // Arbustos de Bayas con lógica de crecimiento
        public static final Block EXPLOSIVE_BERRY_BLOCK = registerBlock("explosive_berry_block",
                        (settings, item) -> new BerryBlock(settings, item),
                        AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).ticksRandomly().noCollision().nonOpaque(),
                        ModItems.EXPLOSIVE_BERRY);

        public static final Block FIRE_BERRY_BLOCK = registerBlock("fire_berry_block",
                        (settings, item) -> new BerryBlock(settings, item),
                        AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).ticksRandomly().noCollision().nonOpaque(),
                        ModItems.FIRE_BERRY);

        public static final Block THUNDER_BERRY_BLOCK = registerBlock("thunder_berry_block",
                        (settings, item) -> new BerryBlock(settings, item),
                        AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).ticksRandomly().noCollision().nonOpaque(),
                        ModItems.THUNDER_BERRY);

        // Bloque básico de aprendizaje (Piedra)
        public static final Block RANDOM_BERRY_BUSH = registerBlock("random_berry_bush",
                        (settings, item) -> new RandomBerryBushBlock(settings),
                        AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f), null);

        /**
         * Registra un bloque y crea automáticamente su BlockItem.
         */
        private static Block registerBlock(String name, BiFunction<AbstractBlock.Settings, Item, Block> factory,
                        AbstractBlock.Settings settings, Item fruitItem) {
                Identifier id = Identifier.of("explotefruit", name);
                RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
                RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);

                settings.registryKey(blockKey);
                Block block = factory.apply(settings, fruitItem);

                registerBlockItem(itemKey, block);
                return Registry.register(Registries.BLOCK, blockKey, block);
        }

        private static Item registerBlockItem(RegistryKey<Item> key, Block block) {
                Item.Settings settings = new Item.Settings().registryKey(key);
                return Registry.register(Registries.ITEM, key, new BlockItem(block, settings));
        }

        private static void addBlocksToBuildingItemGroup(FabricItemGroupEntries entries) {
                entries.add(EXPLOSIVE_BERRY_BLOCK);
                entries.add(FIRE_BERRY_BLOCK);
                entries.add(THUNDER_BERRY_BLOCK);
                entries.add(RANDOM_BERRY_BUSH);
        }

        public static void registerModBlocks() {
                System.out.println("Registrando Mod Blocks para explotefruit");
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                                .register(ModBlocks::addBlocksToBuildingItemGroup);
        }
}
