package dev.cesarplyed.explotefruit;

import dev.cesarplyed.explotefruit.block.ModBlocks;
import dev.cesarplyed.explotefruit.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExploteFruitClient implements ClientModInitializer {
	public static final String MOD_ID = "explotefruit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@SuppressWarnings({ "null", "deprecation", "rawtypes", "unchecked" })
	@Override
	public void onInitializeClient() {
		
		// Capas de renderizado para bloques
		BlockRenderLayerMap.putBlock(ModBlocks.EXPLOSIVE_BERRY_BLOCK, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(ModBlocks.FIRE_BERRY_BLOCK, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(ModBlocks.THUNDER_BERRY_BLOCK, BlockRenderLayer.CUTOUT);

		// Renderizado de las Entidades. Usamos FlyingItemEntityRenderer para que se vean
		// como los items.
		EntityRendererRegistry.register(ModEntities.BERRY_PROJECTILE,
				context -> new FlyingItemEntityRenderer(context));
		
		EntityRendererRegistry.register(ModEntities.PARROT_PROJECTILE,
				context -> new FlyingItemEntityRenderer(context));

		LOGGER.info("ExploteFruit Client Initialized!");
	}
}