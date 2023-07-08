package dev.qixils.antisocial.mixin;

import dev.qixils.antisocial.Antisocial;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "shouldSpawnSprintingParticles", at = @At("HEAD"), cancellable = true)
    private void shouldSpawnSprintingParticles(CallbackInfoReturnable<Boolean> cir) {
        if (Antisocial.SKIP_RENDER && ((Entity)(Object)this) instanceof AbstractClientPlayerEntity && (Object)this != MinecraftClient.getInstance().player) {
            cir.setReturnValue(false);
        }
    }
}
