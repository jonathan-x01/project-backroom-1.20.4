package projectbackroom.jonathanx.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.fluid.BackroomsFlowableFluid;
import projectbackroom.jonathanx.registry.BackroomFluidTags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow protected boolean jumping;
    @Unique
    private FluidState fluidState;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    private boolean isSubmergedAtFeet(TagKey<Fluid> fluid) {
        FluidState fluidState = this.getWorld().getFluidState(this.getBlockPos());
        if (fluidState.isIn(fluid)){
            this.fluidState = fluidState;
            return true;
        }
        return false;
    }

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void onTravel(Vec3d movementInput, CallbackInfo ci){
        // Checks to see if player is submerged in backroom fluids.
        if (isSubmergedAtFeet(BackroomFluidTags.BACKROOM_FLUIDS)){
            // Checks to see if the fluid uses Project Backrooms custom implementations
            if (this.fluidState.getFluid() instanceof BackroomsFlowableFluid backroomsFluid){
                // Gets the fluid block.
                BackroomFluidBlock fluidBlock = backroomsFluid.getFluidBlock();
                // Gets the fluid settings from the fluid block.
                BackroomFluidBlock.Settings settings = fluidBlock.getFluidSettings();
                // Checks to see if the fluid doesn't plan to use the default physics.
                if (!settings.usesDefaultPhysics()){
                    // The gravity.
                    double gravity = 0.08;

                    this.updateVelocity(settings.getSpeed(), movementInput); // Speed
                    this.move(MovementType.SELF, this.getVelocity());

                    Vec3d drag = this.getVelocity().multiply(settings.getDrag().x, settings.getDrag().y, settings.getDrag().z);
                    if (!this.hasNoGravity()) {
                        drag = drag.add(0, -gravity / 4.0, 0);
                    }

                    if (jumping) {
                        drag = drag.add(0.0, 0.04, 0.0); // swim up slowly
                    }

                    this.setVelocity(drag);
                    this.fallDistance = 0.0F;
                    ci.cancel();
                }
            }

        }
    }
}