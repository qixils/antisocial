package dev.qixils.antisocial;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class Antisocial implements ClientModInitializer {

    public static boolean SKIP_RENDER = false;
    public static final KeyBinding TOGGLE_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "antisocial.key.playerRendering",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_H,
        "antisocial.name"
    ));

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_KEY.wasPressed()) {
                SKIP_RENDER = !SKIP_RENDER;
                String message = SKIP_RENDER ? "antisocial.chat.playerRendering.off" : "antisocial.chat.playerRendering.on";
                if (client.player != null) {
                    client.player.sendMessage(Text.translatable(message), false);
                }
            }
        });
    }
}
