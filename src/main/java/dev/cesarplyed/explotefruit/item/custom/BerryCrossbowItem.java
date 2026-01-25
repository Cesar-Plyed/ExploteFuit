package dev.cesarplyed.explotefruit.item.custom;

import dev.cesarplyed.explotefruit.entity.projectile.BerryProjectileEntity;
import dev.cesarplyed.explotefruit.entity.projectile.ParrotProjectileEntity;
import dev.cesarplyed.explotefruit.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.function.Predicate;

public class BerryCrossbowItem extends CrossbowItem {
    public BerryCrossbowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isOf(ModItems.EXPLOSIVE_BERRY) ||
                stack.isOf(ModItems.FIRE_BERRY) ||
                stack.isOf(ModItems.THUNDER_BERRY) ||
                stack.isOf(ModItems.PARROT_AMMO);
    }

    @SuppressWarnings("null")
    @Override
    protected void shootAll(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack,
            List<ItemStack> projectiles, float speed, float divergence, boolean critical,
            @org.jetbrains.annotations.Nullable LivingEntity target) {
        float modifiedSpeed = speed * 0.85f;
        boolean isMultishot = projectiles.size() > 1;

        for (int i = 0; i < projectiles.size(); ++i) {
            ItemStack itemStack = projectiles.get(i);
            if (!itemStack.isEmpty()) {
                // Crear el tipo correcto de proyectil según la munición
                if (itemStack.isOf(ModItems.PARROT_AMMO)) {
                    ParrotProjectileEntity parrotProjectile = new ParrotProjectileEntity(world, shooter, itemStack, stack);
                    if (isMultishot) {
                        parrotProjectile.setMultishot(true);
                    }

                    float yaw = shooter.getYaw();
                    if (i == 1)
                        yaw -= 10.0F;
                    else if (i == 2)
                        yaw += 10.0F;

                    parrotProjectile.setVelocity(shooter, shooter.getPitch(), yaw, 0.0f, modifiedSpeed, divergence);
                    world.spawnEntity(parrotProjectile);
                } else {
                    BerryProjectileEntity berryProjectile = new BerryProjectileEntity(world, shooter, itemStack, stack);
                    if (isMultishot) {
                        berryProjectile.setMultishot(true);
                    }

                    float yaw = shooter.getYaw();
                    if (i == 1)
                        yaw -= 10.0F;
                    else if (i == 2)
                        yaw += 10.0F;

                    berryProjectile.setVelocity(shooter, shooter.getPitch(), yaw, 0.0f, modifiedSpeed, divergence);
                    world.spawnEntity(berryProjectile);
                }
            }
        }

        // Uncharge
        stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
    }
}
