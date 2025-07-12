package projectbackroom.jonathanx.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.fluids.BackroomFluidBlock;
import projectbackroom.jonathanx.fluid.BackroomsFlowableFluid;
import projectbackroom.jonathanx.util.CameraBackroomFluid;
import projectbackroom.jonathanx.util.ColorManager;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMxin {
    @Shadow private static float red;
    @Shadow private static float green;
    @Shadow private static float blue;

    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BackgroundRenderer;getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;", shift = At.Shift.AFTER), cancellable = true)
    private static void onApplyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (CameraBackroomFluid.isSubmergedInBackroomFluid(camera)){
            FluidState fluidState = CameraBackroomFluid.getFluidState();
            if (fluidState.getFluid() instanceof BackroomsFlowableFluid backroomFluid){
                BackroomFluidBlock.Settings settings = backroomFluid.getFluidBlock().getFluidSettings();
                ColorManager fluidColor = settings.getColor();

                red = fluidColor.getRedDecimal();
                green = fluidColor.getGreenDecimal();
                blue = fluidColor.getBlueDecimal();

                RenderSystem.setShaderFogStart(settings.getFogStart());
                RenderSystem.setShaderFogEnd(settings.getFogEnd());

                ci.cancel();
            }
        }
    }
}