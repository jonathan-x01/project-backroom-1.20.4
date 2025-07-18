package projectbackroom.jonathanx.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.http.annotation.Obsolete;
import projectbackroom.jonathanx.ProjectBackroom;

public class BackroomParticleTypes {
    public static final DefaultParticleType CONTAMINATED_WATER_SPLASH = register("contaminated_water_splash", FabricParticleTypes.simple());

    public static final DefaultParticleType ALMOND_WATER_BUBBLE = register("almond_water_bubble", FabricParticleTypes.simple());
    public static final DefaultParticleType CONTAMINATED_WATER_BUBBLE = register("contaminated_water_bubble", FabricParticleTypes.simple());

    public static final DefaultParticleType DRIPPING_ALMOND_WATER = register("dripping_almond_water", FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_ALMOND_WATER = register("falling_almond_water", FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_ALMOND_WATER = register("landing_almond_water", FabricParticleTypes.simple());

    public static final DefaultParticleType DRIPPING_BLACK_SLUDGE = register("dripping_black_sludge", FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_BLACK_SLUDGE = register("falling_black_sludge", FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_BLACK_SLUDGE = register("landing_black_sludge", FabricParticleTypes.simple());

    public static final DefaultParticleType DRIPPING_CONTAMINATED_WATER = register("dripping_contaminated_water", FabricParticleTypes.simple());
    public static final DefaultParticleType FALLING_CONTAMINATED_WATER = register("falling_contaminated_water", FabricParticleTypes.simple());
    public static final DefaultParticleType LANDING_CONTAMINATED_WATER = register("landing_contaminated_water", FabricParticleTypes.simple());
    @Obsolete
    public static final DefaultParticleType SPLASH = register("splash", FabricParticleTypes.simple());

    private static DefaultParticleType register(String name, DefaultParticleType type){
        return Registry.register(Registries.PARTICLE_TYPE, ProjectBackroom.id(name), type);
    }

    public static void registerParticles(){
        ProjectBackroom.displayRegisteredSectors(BackroomParticleTypes.class);
    }
}
