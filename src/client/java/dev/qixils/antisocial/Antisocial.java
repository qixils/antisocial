package dev.qixils.antisocial;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class Antisocial implements ClientModInitializer {
    public static boolean SKIP_RENDER = false;
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
        Identifier.fromNamespaceAndPath("antisocial", "name")
    );
    public static final KeyMapping TOGGLE_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
        "antisocial.key.playerRendering",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_H,
        CATEGORY
    ));

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_KEY.consumeClick()) {
                SKIP_RENDER = !SKIP_RENDER;
                String message = SKIP_RENDER ? "antisocial.chat.playerRendering.off" : "antisocial.chat.playerRendering.on";
                if (client.player != null) {
                    client.player.sendOverlayMessage(Component.translatable(message));
                }
            }
        });
    }
}
