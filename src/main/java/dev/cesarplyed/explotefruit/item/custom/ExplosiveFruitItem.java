package dev.cesarplyed.explotefruit.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Clase personalizada para las frutas explosivas.
 * Extiende de la clase base 'Item' de Minecraft.
 */
public class ExplosiveFruitItem extends Item {

    /**
     * Constructor de la clase.
     * 
     * @param settings Configuraciones del item (como grupo, stack size, etc).
     */
    public ExplosiveFruitItem(Settings settings) {
        super(settings);
    }

    /**
     * Este método se ejecuta cuando la entidad (jugador) termina de consumir el
     * item.
     * Aquí es donde programamos la explosión.
     *
     * @param stack El item que se está consumiendo.
     * @param world El mundo donde ocurre la acción.
     * @param user  La entidad que consume el item.
     * @return El ItemStack resultante (normalmente decrementado).
     */
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // Llamamos al método padre para asegurar que la comida se consuma y aplique sus
        // efectos normales (hambre, saturación).
        ItemStack result = super.finishUsing(stack, world, user);

        // Verificamos si estamos en el "Lado del Servidor" (Server Side).
        // En Minecraft, la lógica del mundo debe ocurrir en el servidor, no en el
        // cliente.
        if (!world.isClient()) {
            // createExplosion crea una explosión en el mundo.
            // Parámetros:
            // 1. 'user': La entidad que causa la explosión (el jugador).
            // 2. user.getX(), user.getY(), user.getZ(): Las coordenadas donde ocurre
            // explota.
            // 3. 4.0F: La fuerza de la explosión (TNT es 4.0F).
            // 4. World.ExplosionSourceType.BLOCK: El tipo de daño al mundo (si rompe
            // bloques).
            world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 4.0F, World.ExplosionSourceType.BLOCK);
        }

        return result;
    }
}
