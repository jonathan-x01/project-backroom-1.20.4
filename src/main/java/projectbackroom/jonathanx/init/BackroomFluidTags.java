package projectbackroom.jonathanx.init;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

public class BackroomFluidTags {
    @Deprecated
    public static final TagKey<Fluid> BLACK_SLUDGE = of("black_sludge");
    public static final TagKey<Fluid> BACKROOM_FLUIDS = of("backroom_fluids");

    private static TagKey<Fluid> of(String id) {
        return TagKey.of(RegistryKeys.FLUID, ProjectBackroom.id(id));
    }

    @Deprecated
    public static void registerFluidTags(){
        DebugLogger.displayRegisteredSectors(BackroomFluidTags.class);
    }
}
