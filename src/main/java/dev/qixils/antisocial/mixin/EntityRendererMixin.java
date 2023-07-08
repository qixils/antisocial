package dev.qixils.antisocial.mixin;

import dev.qixils.antisocial.Antisocial;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "hasLabel", at = @At("HEAD"), cancellable = true)
    public void hasLabel(CallbackInfoReturnable<Boolean> cir) {
        // skip all rendering regardless of entity type
        // since many servers use armor stands or other entities for labels
        if (Antisocial.SKIP_RENDER) {
            cir.setReturnValue(false);
        }
    }
}
