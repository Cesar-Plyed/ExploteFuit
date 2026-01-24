package dev.cesarplyed.explotefruit.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;

/**
 * Un bloque básico creado para aprender la estructura base de un bloque en
 * Minecraft.
 * Se comporta como un bloque sólido normal (tipo piedra).
 */
public class RandomBerryBushBlock extends Block {
    public static final MapCodec<RandomBerryBushBlock> CODEC = createCodec(RandomBerryBushBlock::new);

    @Override
    public MapCodec<? extends RandomBerryBushBlock> getCodec() {
        return CODEC;
    }

    public RandomBerryBushBlock(Settings settings) {
        super(settings);
    }
}
