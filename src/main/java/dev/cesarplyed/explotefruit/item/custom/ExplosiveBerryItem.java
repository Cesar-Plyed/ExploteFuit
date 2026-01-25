package dev.cesarplyed.explotefruit.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosiveBerryItem extends BlockItem {
    public ExplosiveBerryItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        if (!world.isClient()) {
            world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 4.0F, World.ExplosionSourceType.BLOCK);
        }
        return result;
    }
}
