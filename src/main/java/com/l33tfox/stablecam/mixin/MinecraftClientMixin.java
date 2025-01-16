package com.l33tfox.stablecam.mixin;

import com.l33tfox.stablecam.StableCamDuck;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Final public GameOptions options;

    // modify while statement in handleInputEvents() to also check that player is not using stable cam before changing perspective
    @ModifyExpressionValue(method = "handleInputEvents", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/option/KeyBinding;wasPressed()Z", ordinal = 1))
    private boolean disableF5PerspectiveChangeInTopDown(boolean original) {
        return original && !((StableCamDuck) options).stablecam$isStableCam();
    }
}
