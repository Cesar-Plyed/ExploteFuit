package dev.cesarplyed.explotefruit;

import dev.cesarplyed.explotefruit.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExploteFruitClient implements ClientModInitializer {
	public static final String MOD_ID = "explotefruit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		// En 1.21.11, si BlockRenderLayerMap no se encuentra en el classpath de
		// compilación,
		// usaremos un comentario por ahora hasta confirmar si se requiere una
		// dependencia extra
		// o si el paquete es diferente (ej.
		// net.fabricmc.fabric.api.client.rendering.v1)

		LOGGER.info("ExploteFruit Client Initialized - Waiting for stable BlockRenderLayerMap API");
	}
}