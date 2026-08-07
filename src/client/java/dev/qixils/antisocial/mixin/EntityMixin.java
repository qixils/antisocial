package dev.qixils.antisocial.mixin;

import dev.qixils.antisocial.Antisocial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "canSpawnSprintParticle", at = @At("HEAD"), cancellable = true)
    private void canSpawnSprintParticle(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity)(Object)this;
        Minecraft client = Minecraft.getInstance();
        boolean isOtherPlayer = entity instanceof AbstractClientPlayer && entity != client.player;

        if (Antisocial.SKIP_RENDER && isOtherPlayer) {
            cir.setReturnValue(false);
        }
    }
}
