package projectbackroom.jonathanx.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.profiler.Profiler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Environment(EnvType.CLIENT)
public class BackroomParticleManager implements ResourceReloader {
    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        return null;
    }

    public static void registerDefaultFactories(){
        registerPipeLeakFactory(BackroomParticleTypes.DRIPPING_ALMOND_WATER, BackroomPipeLeakParticle::createDrippingAlmondWater);
        registerPipeLeakFactory(BackroomParticleTypes.FALLING_ALMOND_WATER, BackroomPipeLeakParticle::createFallingAlmondWater);

        registerPipeLeakFactory(BackroomParticleTypes.DRIPPING_BLACK_SLUDGE, BackroomPipeLeakParticle::createDrippingBlackSludge);
        registerPipeLeakFactory(BackroomParticleTypes.FALLING_BLACK_SLUDGE, BackroomPipeLeakParticle::createFallingBlackSludge);
        registerPipeLeakFactory(BackroomParticleTypes.LANDING_BLACK_SLUDGE, BackroomPipeLeakParticle::createLandingBlackSludge);

        registerPipeLeakFactory(BackroomParticleTypes.DRIPPING_CONTAMINATED_WATER, BackroomPipeLeakParticle::createDrippingContaminatedWater);
        registerPipeLeakFactory(BackroomParticleTypes.FALLING_CONTAMINATED_WATER, BackroomPipeLeakParticle::createFallingContaminatedWater);
    }

    public static <T extends ParticleEffect> void registerFactory(ParticleType<T> type, ParticleFactory<T> factory){
        ParticleFactoryRegistry.getInstance().register(type, factory);
    }

    public static <T extends ParticleEffect> void registerPipeLeakFactory(ParticleType<T> type, ParticleFactory.BlockLeakParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(type, spriteProvider ->
                (parameters, world, x, y, z, vx, vy, vz) -> {
                    SpriteBillboardParticle particle = factory.createParticle(parameters, world, x, y, z, vx, vy, vz);
                    if (particle != null){
                        particle.setSprite(spriteProvider);
                    }
                    return particle;
                });
    }
}
