package com.l33tfox.stablecam.mixin;

import com.l33tfox.stablecam.StableCamDuck;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin implements StableCamDuck {

    @Unique
    private boolean inStableCam = false;

    public boolean stablecam$isStableCam() {
        return inStableCam;
    }

    public void stablecam$setStableCam(boolean isStableCam) {
        inStableCam = isStableCam;
    }

}
