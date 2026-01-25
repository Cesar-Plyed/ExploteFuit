package dev.cesarplyed.explotefruit.command;

import com.mojang.brigadier.arguments.FloatArgumentType;
import dev.cesarplyed.explotefruit.entity.projectile.BerryProjectileEntity;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ModCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("explotefruit")
                    .then(literal("set_multiplier")
                            .then(argument("value", FloatArgumentType.floatArg(0.0f))
                                    .executes(context -> {
                                        float value = FloatArgumentType.getFloat(context, "value");
                                        BerryProjectileEntity.EXPLOSION_MULTIPLIER = value;
                                        context.getSource().sendFeedback(
                                                () -> Text.literal("Explosión modificada - Multiplicador: " + value),
                                                true);
                                        return 1;
                                    }))));
        });
    }
}
