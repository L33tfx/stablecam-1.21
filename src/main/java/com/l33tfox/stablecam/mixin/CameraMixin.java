package com.l33tfox.stablecam.mixin;

import com.l33tfox.stablecam.StableCamDuck;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin implements StableCamDuck {

    @Unique
    private boolean inStableCam = false;

    public boolean stablecam$isStableCam() {
        return inStableCam;
    }

    public void stablecam$setStableCam(boolean isStableCam) {
        inStableCam = isStableCam;
    }

    // Stop camera from rotating with player's head if in stable cam view
    @Inject(method = "setRotation", at = @At("HEAD"), cancellable = true)
    private void stopRotationInStableCam(float yaw, float pitch, CallbackInfo ci) {
        if (stablecam$isStableCam()) {
            ci.cancel();
        }
    }
}
