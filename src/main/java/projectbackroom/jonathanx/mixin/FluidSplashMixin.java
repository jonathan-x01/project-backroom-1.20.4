package projectbackroom.jonathanx.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import projectbackroom.jonathanx.fluid.BackroomFluids;
import projectbackroom.jonathanx.particle.BackroomParticleTypes;

@Mixin(Entity.class)
public abstract class FluidSplashMixin {
    @Shadow public abstract World getWorld();

    @ModifyArg(
            method = "onSwimmingStart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
            ),
            index = 0
    )
    private ParticleEffect redirectSplashParticle(ParticleEffect original) {
        Entity self = (Entity) (Object) this;

        if (original == ParticleTypes.SPLASH){
            if (isInAlmondWater(self)){
                return BackroomParticleTypes.LANDING_ALMOND_WATER;
            } else if (isInContaminatedWater(self)){
                return BackroomParticleTypes.CONTAMINATED_WATER_SPLASH;
            }
        } else if (original == ParticleTypes.BUBBLE){
            if (isInAlmondWater(self)){
                return BackroomParticleTypes.ALMOND_WATER_BUBBLE;
            } else if (isInContaminatedWater(self)){
                return BackroomParticleTypes.CONTAMINATED_WATER_BUBBLE;
            }
        }
        return original;
    }

    @Unique
    private boolean isInAlmondWater(Entity entity){
        return isInFluidCollection(entity, BackroomFluids.ALMOND_WATER, BackroomFluids.FLOWING_ALMOND_WATER);
    }

    @Unique
    private boolean isInContaminatedWater(Entity entity){
        return isInFluidCollection(entity, BackroomFluids.CONTAMINATED_WATER, BackroomFluids.FLOWING_CONTAMINATED_WATER);
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
