package dev.cesarplyed.explotefruit.item;

import dev.cesarplyed.explotefruit.item.custom.BerryCrossbowItem;
import dev.cesarplyed.explotefruit.item.custom.ExplosiveBerryItem;
import dev.cesarplyed.explotefruit.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

/**
 * Clase para registrar todos los ítems del mod.
 */
public class ModItems {

        // Definimos un componente de comida base.
        // nutrition(2): Restaura 1 muslo de comida.
        // saturationModifier(0.2f): Poca saturación.
        public static final FoodComponent EXPLOSIVE_FOOD_COMPONENT = new FoodComponent.Builder().nutrition(2)
                        .saturationModifier(0.2f).build();

        // 1. Baya Explosiva
        // Usamos nuestra clase personalizada 'ExplosiveBerryItem' para que actúe como
        // semilla y explote al comer.
        public static final Item EXPLOSIVE_BERRY = registerItem("explosive_berry",
                        (settings) -> new ExplosiveBerryItem(ModBlocks.EXPLOSIVE_BERRY_BLOCK, settings),
                        new Item.Settings().food(EXPLOSIVE_FOOD_COMPONENT));

        // 2. Baya de Fuego
        public static final Item FIRE_BERRY = registerItem("fire_berry",
                        (settings) -> new ExplosiveBerryItem(ModBlocks.FIRE_BERRY_BLOCK, settings),
                        new Item.Settings().food(EXPLOSIVE_FOOD_COMPONENT));

        // 3. Baya de Trueno
        public static final Item THUNDER_BERRY = registerItem("thunder_berry",
                        (settings) -> new ExplosiveBerryItem(ModBlocks.THUNDER_BERRY_BLOCK, settings),
                        new Item.Settings().food(EXPLOSIVE_FOOD_COMPONENT));

        // 4. Ballesta de Bayas
        public static final Item BERRY_CROSSBOW = registerItem("berry_crossbow", BerryCrossbowItem::new,
                        new Item.Settings().maxCount(1));

        // 5. Baya de Azar
        public static final Item RANDOM_BERRY = registerItem("random_berry",
                        (settings) -> new ExplosiveBerryItem(ModBlocks.RANDOM_BERRY_BUSH, settings),
                        new Item.Settings().food(EXPLOSIVE_FOOD_COMPONENT));

        // 6. Munición de Loro
        public static final Item PARROT_AMMO = registerItem("parrot_ammo", Item::new,
                        new Item.Settings().maxCount(1));

        /**
         * Método auxiliar para registrar un ítem en el juego.
         * En versiones recientes (1.21.2+), el ítem necesita saber su ID (RegistryKey)
         * al crearse.
         *
         * @param name     El nombre del ítem.
         * @param factory  Una función que crea el ítem usando los settings.
         * @param settings Las configuraciones del ítem.
         * @return El ítem registrado.
         */
        private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
                // Creamos la llave de registro (ID) primero.
                RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("explotefruit", name));

                // Asignamos la llave a los settings. Esto es crucial para evitar el crash "Item
                // id not set".
                settings.registryKey(key);

                // Creamos el ítem usando la fábrica y pasándole los settings ya configurados.
                Item item = factory.apply(settings);

                // Finalmente registramos el ítem usando la llave y la instancia creada.
                return Registry.register(Registries.ITEM, key, item);
        }

        /**
         * Añade nuestros ítems al grupo de "Comida y Bebida" en el inventario creativo.
         * 
         * @param entries Las entradas del grupo de ítems.
         */
        private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
                entries.add(EXPLOSIVE_BERRY);
                entries.add(FIRE_BERRY);
                entries.add(THUNDER_BERRY);
                entries.add(BERRY_CROSSBOW);
        }

        /**
         * Método para inicializar la clase ModItems.
         * Se llama desde la clase principal del mod.
         */
        @SuppressWarnings("null")
        public static void registerModItems() {
                System.out.println("Registrando Mod Items para explotefruit");

                // Añadimos el evento para modificar el grupo de ítems de Comida.
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                                .register(ModItems::addItemsToIngredientItemGroup);
        }
}
