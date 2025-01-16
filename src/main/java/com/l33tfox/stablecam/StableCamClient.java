package com.l33tfox.stablecam;

import com.l33tfox.stablecam.util.StableCamClientUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class StableCamClient implements ClientModInitializer {

    public static KeyBinding toggleKeyBinding;

    @Override
    public void onInitializeClient() {

        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.stablecam.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F6,
                "category.stablecam"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(StableCamClientUtil::playerTick);
    }
}
