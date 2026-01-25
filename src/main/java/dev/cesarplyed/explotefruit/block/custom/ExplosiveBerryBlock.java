package dev.cesarplyed.explotefruit.block.custom;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.MapCodec;

import dev.cesarplyed.explotefruit.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * Arbusto de Bayas Explosivas específico
 */
public class ExplosiveBerryBlock extends BerryBlock {
    public static final MapCodec<@NonNull ExplosiveBerryBlock> CODEC = createCodec(settings -> new ExplosiveBerryBlock(settings));

    public ExplosiveBerryBlock(AbstractBlock.Settings settings) {
        super(settings, ModItems.EXPLOSIVE_BERRY);
    }

    @Override
    protected MapCodec<? extends BerryBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int age = state.get(AGE);
        if (age > 1) {
            int count = 1 + world.random.nextInt(2);
            if (age == 3)
                count++;

            // Siempre soltar Explosive Berries
            Block.dropStack(world, pos, new ItemStack(ModItems.EXPLOSIVE_BERRY, count));
            
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
}