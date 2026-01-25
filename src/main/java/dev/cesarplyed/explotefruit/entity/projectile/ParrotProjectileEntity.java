package dev.cesarplyed.explotefruit.entity.projectile;

import dev.cesarplyed.explotefruit.entity.ModEntities;
import dev.cesarplyed.explotefruit.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ParrotProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    @SuppressWarnings("unused")
    private boolean isMultishot = false;

    public ParrotProjectileEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public ParrotProjectileEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack weapon) {
        super(ModEntities.PARROT_PROJECTILE, owner, world, stack, weapon);
    }

    public void setMultishot(boolean multishot) {
        this.isMultishot = multishot;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        spawnParrot(blockHitResult.getPos());
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        spawnParrot(entityHitResult.getPos());
    }

    private void spawnParrot(Vec3d pos) {
        if (!this.getEntityWorld().isClient()) {
            ParrotEntity parrot = EntityType.PARROT.create(this.getEntityWorld(), SpawnReason.TRIGGERED);
            if (parrot != null) {
                parrot.refreshPositionAndAngles(pos.x, pos.y, pos.z, this.getYaw(), 0.0F);
                this.getEntityWorld().spawnEntity(parrot);

                // Explosión pequeña al aterrizar (¡Cuidado con el loro!)
                this.getEntityWorld().createExplosion(this, pos.x, pos.y, pos.z, 2.0f, World.ExplosionSourceType.NONE);
            }
            this.discard();
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.PARROT_AMMO);
    }

    @Override
    public ItemStack getStack() {
        return this.getItemStack();
    }
}
