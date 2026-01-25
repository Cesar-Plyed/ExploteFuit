package dev.cesarplyed.explotefruit.entity;

import dev.cesarplyed.explotefruit.ExploteFruit;
import dev.cesarplyed.explotefruit.entity.projectile.BerryProjectileEntity;
import dev.cesarplyed.explotefruit.entity.projectile.ParrotProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModEntities {
    public static final EntityType<BerryProjectileEntity> BERRY_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("explotefruit", "berry_projectile"),
            EntityType.Builder.<BerryProjectileEntity>create(BerryProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of("explotefruit", "berry_projectile"))));

    public static final EntityType<ParrotProjectileEntity> PARROT_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("explotefruit", "parrot_projectile"),
            EntityType.Builder.<ParrotProjectileEntity>create(ParrotProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of("explotefruit", "parrot_projectile"))));

    public static void registerModEntities() {
        ExploteFruit.LOGGER.info("Registering Mod Entities for " + ExploteFruit.MOD_ID);
    }
}
