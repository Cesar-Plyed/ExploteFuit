package dev.cesarplyed.explotefruit.entity.projectile;

import dev.cesarplyed.explotefruit.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.FlyingItemEntity;

public class BerryProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private Vec3d origin;
    private boolean isMultishot = false;
    public static float EXPLOSION_MULTIPLIER = 1.0f;

    public BerryProjectileEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public BerryProjectileEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack weapon) {
        super(ModEntities.BERRY_PROJECTILE, owner, world, stack, weapon);
        this.origin = new Vec3d(owner.getX(), owner.getY(), owner.getZ());
    }

    public void setMultishot(boolean multishot) {
        this.isMultishot = multishot;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient()) {
            float strength = 4.0f;

            if (!isMultishot && origin != null) {
                double distance = origin.distanceTo(new Vec3d(this.getX(), this.getY(), this.getZ()));
                // Aumenta la explosión hasta un límite de 20 bloques.
                strength = (float) (4.0f + (Math.min(distance, 20.0) / 4.0) * EXPLOSION_MULTIPLIER);
            }

            this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), strength,
                    World.ExplosionSourceType.BLOCK);
            this.discard();
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(Items.SWEET_BERRIES);
    }

    @Override
    public ItemStack getStack() {
        return this.getItemStack();
    }
}
