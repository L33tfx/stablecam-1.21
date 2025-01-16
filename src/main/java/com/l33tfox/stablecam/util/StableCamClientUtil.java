package com.l33tfox.stablecam.util;

import com.l33tfox.stablecam.StableCamClient;
import com.l33tfox.stablecam.StableCamDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Perspective;

public class StableCamClientUtil {

    // Called at the end of every tick on the client using a CLIENT_END_TICK event registered in StableCamClient class
    public static void playerTick(MinecraftClient client) {
        ClientPlayerEntity player = client.player;

        // if player exists
        if (player != null) {
            StableCamDuck options = (StableCamDuck) client.options;

            // detect key press (this is how MinecraftClient's handleInputEvents() does it)
            while (StableCamClient.toggleKeyBinding.wasPressed()) {
                options.stablecam$setStableCam(!options.stablecam$isStableCam());

                if (client.options.getPerspective().isFirstPerson())
                    client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
                else client.options.setPerspective(Perspective.FIRST_PERSON);
            }

            if (options.stablecam$isStableCam())
                // fixes a bug where toggling f5 will cause the hand to show when in stable cam view
                client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        }
    }
}
