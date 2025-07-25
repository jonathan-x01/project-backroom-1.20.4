package projectbackroom.jonathanx.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.fluid.FluidState;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.fluid.BackroomsFlowableFluid;
import projectbackroom.jonathanx.util.CameraBackroomFluid;
import projectbackroom.jonathanx.util.ColorManager;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMxin {
    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BackgroundRenderer;getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;", shift = At.Shift.AFTER), cancellable = true)
    private static void onApplyFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        if (CameraBackroomFluid.isSubmergedInBackroomFluid(camera)){
            FluidState fluidState = CameraBackroomFluid.getFluidState();
            if (fluidState.getFluid() instanceof BackroomsFlowableFluid backroomFluid){
                BackroomFluidBlock.Settings settings = backroomFluid.getFluidBlock().getFluidSettings();
                ColorManager fluidColor = settings.getColor();
                Fog fog = new Fog(
                        settings.getFogStart(),
                        settings.getFogEnd(),
                        FogShape.SPHERE,
                        fluidColor.getRedDecimal(),
                        fluidColor.getGreenDecimal(),
                        fluidColor.getBlueDecimal(),
                        settings.getFogAlpha()
                );
                RenderSystem.setShaderFog(fog);
                cir.setReturnValue(fog);
                cir.cancel();
            }
        }
    }
}