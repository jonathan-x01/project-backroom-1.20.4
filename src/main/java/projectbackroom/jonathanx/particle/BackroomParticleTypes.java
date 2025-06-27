package projectbackroom.jonathanx.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;

public class BackroomParticleTypes {
    public static final DefaultParticleType DRIPPING_ALMOND_WATER = register("dripping_almond_water", FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_ALMOND_WATER = register("falling_almond_water", FabricParticleTypes.simple());
    public static final DefaultParticleType DRIPPING_BLACK_SLUDGE = register("dripping_black_sludge", FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_BLACK_SLUDGE = register("falling_black_sludge", FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_BLACK_SLUDGE = register("landing_black_sludge", FabricParticleTypes.simple());
    public static final DefaultParticleType DRIPPING_CONTAMINATED_WATER = register("dripping_contaminated_water", FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_CONTAMINATED_WATER = register("falling_contaminated_water", FabricParticleTypes.simple());

    private static DefaultParticleType register(String name, DefaultParticleType type){
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(ProjectBackroom.MOD_ID,name), type);
    }

    public static void registerParticles(){
        ProjectBackroom.displayRegisteredSectors(BackroomParticleTypes.class);
    }
}
