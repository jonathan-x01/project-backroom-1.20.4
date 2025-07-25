package projectbackroom.jonathanx.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectbackroom.jonathanx.init.BackroomFluidTags;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "updateMovementInFluid", at = @At("HEAD"), cancellable = true)
    private void applyViscousFluidPhysics(TagKey<Fluid> tag, double speed, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity)(Object) this;

        if (tag == BackroomFluidTags.BLACK_SLUDGE) {
            cir.setReturnValue(true); // report that the entity is in this fluid
        }
    }
}
