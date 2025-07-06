package projectbackroom.jonathanx.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import projectbackroom.jonathanx.fluid.BackroomFluids;
import projectbackroom.jonathanx.particle.BackroomParticleTypes;

@Mixin(Entity.class)
public abstract class FluidSplashMixin {
    @Redirect(
            method = "onSwimmingStart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V",
                    ordinal = 0
            )
    )
    private void redirectSplashParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        Entity self = (Entity) (Object) this;
        if (isInFluidCollection(self, BackroomFluids.ALMOND_WATER, BackroomFluids.FLOWING_ALMOND_WATER)){
            instance.addParticle(BackroomParticleTypes.LANDING_ALMOND_WATER, x, y, z, velocityX, velocityY, velocityZ);
        } else if (isInFluidCollection(self, BackroomFluids.CONTAMINATED_WATER, BackroomFluids.FLOWING_CONTAMINATED_WATER)){
            instance.addParticle(BackroomParticleTypes.CONTAMINATED_WATER_SPLASH, x, y, z, velocityX, velocityY, velocityZ);
        }else {
            instance.addParticle(parameters, x, y, z, velocityX, velocityY, velocityZ);
        }
    }

    @Redirect(
            method = "onSwimmingStart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V",
                    ordinal = 1
            )
    )
    private void redirectBubbleParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        Entity self = (Entity) (Object) this;
        if (isInFluidCollection(self, BackroomFluids.ALMOND_WATER, BackroomFluids.FLOWING_ALMOND_WATER)){
            instance.addParticle(BackroomParticleTypes.ALMOND_WATER_BUBBLE, x, y, z, velocityX, velocityY, velocityZ);
        } else if (isInFluidCollection(self, BackroomFluids.CONTAMINATED_WATER, BackroomFluids.FLOWING_CONTAMINATED_WATER)){
            instance.addParticle(BackroomParticleTypes.CONTAMINATED_WATER_BUBBLE, x, y, z, velocityX, velocityY, velocityZ);
        }else {
            instance.addParticle(parameters, x, y, z, velocityX, velocityY, velocityZ);
        }
    }

    @Unique
    private boolean isInFluidCollection(Entity entity, FlowableFluid fluid1, FlowableFluid fluid2){
        return isInFluid(entity, fluid1) || isInFluid(entity, fluid2);
    }

    @Unique
    private boolean isInFluid(Entity entity, Fluid fluid) {
        BlockPos pos = entity.getBlockPos();
        FluidState fluidState = entity.getWorld().getFluidState(pos);
        return fluidState.isOf(fluid);
    }
}
