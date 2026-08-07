package dev.qixils.antisocial.mixin;

import dev.qixils.antisocial.Antisocial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    public <E extends Entity> void shouldRender(E entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        Minecraft client = Minecraft.getInstance();
        boolean isOtherPlayer = entity instanceof AbstractClientPlayer && entity != client.player;

        if (Antisocial.SKIP_RENDER && isOtherPlayer) {
            cir.setReturnValue(false);
        }
    }
}
