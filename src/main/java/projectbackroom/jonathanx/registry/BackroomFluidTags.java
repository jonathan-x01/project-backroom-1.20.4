package projectbackroom.jonathanx.registry;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.apache.http.annotation.Obsolete;
import projectbackroom.jonathanx.ProjectBackroom;

public class BackroomFluidTags {
    @Deprecated
    public static final TagKey<Fluid> BLACK_SLUDGE = of("black_sludge");
    public static final TagKey<Fluid> BACKROOM_FLUIDS = of("backroom_fluids");

    private static TagKey<Fluid> of(String id) {
        return TagKey.of(RegistryKeys.FLUID, ProjectBackroom.id(id));
    }

    public static void registerFluidTags(){
        ProjectBackroom.displayRegisteredSectors(BackroomFluidTags.class);
    }
}
