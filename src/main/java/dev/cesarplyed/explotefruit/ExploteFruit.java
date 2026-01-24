package dev.cesarplyed.explotefruit;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExploteFruit implements ModInitializer {
	public static final String MOD_ID = "explotefruit";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Este código se ejecuta cuando Minecraft está listo para cargar mods.

		// Inicializamos (registramos) nuestros items llamando al método estático que
		// creamos.
		dev.cesarplyed.explotefruit.item.ModItems.registerModItems();

		// Inicializamos (registramos) nuestros bloques.
		dev.cesarplyed.explotefruit.block.ModBlocks.registerModBlocks();

		LOGGER.info("Hello Fabric world!");
	}
}