package projectbackroom.jonathanx.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;

public class BackroomFluids {
    public static FlowableFluid ALMOND_WATER;
    public static FlowableFluid FLOWING_ALMOND_WATER;

    private static <T extends Fluid> T register(String id, T value) {
        return Registry.register(Registries.FLUID, ProjectBackroom.id(id), value);
    }

    public static void registerFluid(){
        ProjectBackroom.displayRegisteredSectors(BackroomFluids.class);

        FLOWING_ALMOND_WATER = register("flowing_almond_water", new AlmondWaterFluid.Flowing());
        ALMOND_WATER = register("almond_water", new AlmondWaterFluid.Still());
    }
}
