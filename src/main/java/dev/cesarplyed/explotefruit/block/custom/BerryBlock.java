package dev.cesarplyed.explotefruit.block.custom;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.MapCodec;

import dev.cesarplyed.explotefruit.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

/**
 * Bloque de arbusto de bayas personalizado con etapas de crecimiento.
 */
public class BerryBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<@NonNull BerryBlock> CODEC = createCodec(
        settings -> new BerryBlock(settings, ModItems.EXPLOSIVE_BERRY));
    public static final IntProperty AGE = Properties.AGE_3;

    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    private final Item fruitItem;

    public BerryBlock(Settings settings, Item fruitItem) {
        super(settings);
        this.fruitItem = fruitItem;
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }



    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        // Devolver el BlockItem del arbusto, no la baya
        // Esto previene que suelte bayas al ser colocado
        return new ItemStack(this.asItem());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int age = state.get(AGE);
        if (age == 0) {
            return SMALL_SHAPE;
        } else {
            return age < 3 ? LARGE_SHAPE : super.getOutlineShape(state, world, pos, context);
        }
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = state.get(AGE);
        if (age < 3 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
            BlockState newState = state.with(AGE, age + 1);
            world.setBlockState(pos, newState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(newState));
        }
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity,
            EntityCollisionHandler handler, boolean bl) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX
                && entity.getType() != EntityType.BEE) {
            entity.slowMovement(state, new Vec3d(0.8F, 0.75, 0.8F));
            if (world instanceof ServerWorld serverWorld) {
                if (state.get(AGE) != 0) {
                    Vec3d vec3d = entity.isControlledByPlayer() ? entity.getMovement()
                            : entity.getLastRenderPos().subtract(entity.getEntityPos());
                    if (vec3d.horizontalLengthSquared() > 0.0) {
                        double dx = Math.abs(vec3d.getX());
                        double dz = Math.abs(vec3d.getZ());
                        if (dx >= 0.003F || dz >= 0.003F) {
                            entity.damage(serverWorld, world.getDamageSources().sweetBerryBush(), 1.0F);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int age = state.get(AGE);
        if (age > 1) {
            int count = 1 + world.random.nextInt(2);
            if (age == 3)
                count++;

            // Verificar que fruitItem no sea null antes de usarlo
            if (this.fruitItem != null) {
                Block.dropStack(world, pos, new ItemStack(this.fruitItem, count));
            } else {
                // Si fruitItem es null, usar una baya por defecto
                Block.dropStack(world, pos, new ItemStack(dev.cesarplyed.explotefruit.item.ModItems.EXPLOSIVE_BERRY, count));
            }
            
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F,
                    0.8F + world.random.nextFloat() * 0.4F);

            BlockState newState = state.with(AGE, 1);
            world.setBlockState(pos, newState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = Math.min(3, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, age), 2);
    }
}
