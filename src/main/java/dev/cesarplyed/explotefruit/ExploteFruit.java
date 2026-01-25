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

	@SuppressWarnings("null")
	@Override
	public void onInitialize() {
		// Este código se ejecuta cuando Minecraft está listo para cargar mods.

		// Inicializamos (registramos) nuestros items llamando al método estático que
		// creamos.
		dev.cesarplyed.explotefruit.item.ModItems.registerModItems();

		// Inicializamos (registramos) nuestros bloques.
		dev.cesarplyed.explotefruit.block.ModBlocks.registerModBlocks();

		// Inicializamos Entidades y Comandos
		dev.cesarplyed.explotefruit.entity.ModEntities.registerModEntities();
		dev.cesarplyed.explotefruit.command.ModCommands.register();

		// Registro de Eventos: Alimentar Mobs
		net.fabricmc.fabric.api.event.player.UseEntityCallback.EVENT
				.register((player, world, hand, entity, hitResult) -> {
					net.minecraft.item.ItemStack stack = player.getStackInHand(hand);

					// Si el item es una de nuestras bayas
					if (stack.getItem() instanceof dev.cesarplyed.explotefruit.item.custom.ExplosiveBerryItem) {
						if (!world.isClient()) {
							// 1. Efecto Visual (Partículas)
							((net.minecraft.server.world.ServerWorld) world).spawnParticles(
									net.minecraft.particle.ParticleTypes.HAPPY_VILLAGER,
									entity.getX(), entity.getY() + 1.0, entity.getZ(),
									10, 0.5, 0.5, 0.5, 0.1);

							// 2. Lógica Especial: Loro -> Munición
							if (entity instanceof net.minecraft.entity.passive.ParrotEntity) {
								// El loro se convierte en item
								entity.discard();
								player.getInventory().offerOrDrop(new net.minecraft.item.ItemStack(
										dev.cesarplyed.explotefruit.item.ModItems.PARROT_AMMO));
								world.playSound(null, player.getX(), player.getY(), player.getZ(),
										net.minecraft.sound.SoundEvents.ENTITY_PARROT_EAT,
										net.minecraft.sound.SoundCategory.PLAYERS, 1.0f, 1.0f,
										world.getRandom().nextLong());
							} else {
								// Otros mobs simplemente hacen un sonido y consumen la baya
								world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
										net.minecraft.sound.SoundEvents.ENTITY_GENERIC_EAT,
										net.minecraft.sound.SoundCategory.NEUTRAL, 1.0f, 1.0f,
										world.getRandom().nextLong());
							}

							// Consumir el item si no es creativo
							if (!player.isCreative()) {
								stack.decrement(1);
							}
						}
						return net.minecraft.util.ActionResult.SUCCESS;
					}
					return net.minecraft.util.ActionResult.PASS;
				});

		LOGGER.info("ExploteFruit Initialized! Parrot Cannon ready.");
	}
}