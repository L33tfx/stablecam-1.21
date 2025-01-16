package com.l33tfox.stablecam.mixin;

import com.l33tfox.stablecam.StableCamDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    private final Camera camera;

    @Shadow
    private final MinecraftClient client;

    protected GameRendererMixin(Camera camera, MinecraftClient client) {
        this.camera = camera;
        this.client = client;
    }

    // before camera.update() is called, set camera to be in or out of stable cam view
    @Inject(method = "renderWorld", at = @At(value = "INVOKE", shift = At.Shift.AFTER,
            target = "Lnet/minecraft/world/tick/TickManager;shouldSkipTick(Lnet/minecraft/entity/Entity;)Z"))
    private void overrideCameraUpdate(RenderTickCounter tickCounter, CallbackInfo ci) {
        boolean isInStableCam = ((StableCamDuck) client.options).stablecam$isStableCam();

        ((StableCamDuck) camera).stablecam$setStableCam(isInStableCam);
    }
}
