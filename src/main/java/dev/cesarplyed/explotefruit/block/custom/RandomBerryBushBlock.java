package dev.cesarplyed.explotefruit.block.custom;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.MapCodec;

import dev.cesarplyed.explotefruit.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

/**
 * Arbusto de bayas aleatorias que suelta diferentes tipos de bayas explosivas.
 * Hereda de BerryBlock para tener la misma lógica de crecimiento y cosecha.
 */
public class RandomBerryBushBlock extends BerryBlock {
    public static final MapCodec<@NonNull RandomBerryBushBlock> CODEC = createCodec(RandomBerryBushBlock::new);

    public RandomBerryBushBlock(Settings settings) {
        super(settings, ModItems.EXPLOSIVE_BERRY); // Por defecto usa explosive berry
    }

    @Override
    protected MapCodec<? extends BerryBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, net.minecraft.entity.player.PlayerEntity player, BlockHitResult hit) {
        int age = state.get(AGE);
        if (age > 1) {
            int count = 1 + world.random.nextInt(2);
            if (age == 3)
                count++;

            // Soltar bayas aleatorias
            ItemStack randomBerry = getRandomBerry(world.getRandom());
            if (!randomBerry.isEmpty()) {
                Block.dropStack(world, pos, new ItemStack(randomBerry.getItem(), count));
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
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        // Devolver una baya aleatoria al romper el bloque
        // Usamos Random.create() con una semilla basada en la posición para consistencia
        return getRandomBerry(Random.create(pos.hashCode()));
    }

    /**
     * Devuelve una baya aleatoria entre las disponibles
     */
    private ItemStack getRandomBerry(Random random) {
        int choice = random.nextInt(4);
        return switch (choice) {
            case 0 -> new ItemStack(ModItems.EXPLOSIVE_BERRY);
            case 1 -> new ItemStack(ModItems.FIRE_BERRY);
            case 2 -> new ItemStack(ModItems.THUNDER_BERRY);
            case 3 -> new ItemStack(ModItems.RANDOM_BERRY);
            default -> new ItemStack(ModItems.EXPLOSIVE_BERRY);
        };
    }
}
