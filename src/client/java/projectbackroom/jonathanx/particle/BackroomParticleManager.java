package projectbackroom.jonathanx.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import projectbackroom.jonathanx.init.BackroomParticleTypes;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class BackroomParticleManager implements ResourceReloader {
    public static void registerDefaultFactories(){
        registerSplashFactory(BackroomParticleTypes.SPLASH, WaterSplashParticle.SplashFactory::new);
        registerFactory(BackroomParticleTypes.CONTAMINATED_WATER_SPLASH, ContaminatedWaterSplash.Factory::new);
        registerFactory(BackroomParticleTypes.ALMOND_WATER_BUBBLE, Bubble.AlmondWaterBubbleFactory::new);
        registerFactory(BackroomParticleTypes.CONTAMINATED_WATER_BUBBLE, Bubble.ContaminatedWaterBubbleFactory::new);

        registerPipeLeakFactory(BackroomParticleTypes.DRIPPING_ALMOND_WATER, BackroomPipeLeakParticle::createDrippingAlmondWater);
        registerPipeLeakFactory(BackroomParticleTypes.FALLING_ALMOND_WATER, BackroomPipeLeakParticle::createFallingAlmondWater);
        registerPipeLeakFactory(BackroomParticleTypes.LANDING_ALMOND_WATER, BackroomPipeLeakParticle::createLandingAlmondWater);

        registerPipeLeakFactory(BackroomParticleTypes.DRIPPING_BLACK_SLUDGE, BackroomPipeLeakParticle::createDrippingBlackSludge);
        registerPipeLeakFactory(BackroomParticleTypes.FALLING_BLACK_SLUDGE, BackroomPipeLeakParticle::createFallingBlackSludge);
        registerPipeLeakFactory(BackroomParticleTypes.LANDING_BLACK_SLUDGE, BackroomPipeLeakParticle::createLandingBlackSludge);

        registerPipeLeakFactory(BackroomParticleTypes.DRIPPING_CONTAMINATED_WATER, BackroomPipeLeakParticle::createDrippingContaminatedWater);
        registerPipeLeakFactory(BackroomParticleTypes.FALLING_CONTAMINATED_WATER, BackroomPipeLeakParticle::createFallingContaminatedWater);
        registerPipeLeakFactory(BackroomParticleTypes.LANDING_CONTAMINATED_WATER, BackroomPipeLeakParticle::createLandingContaminatedWater);
    }

    public static <T extends ParticleEffect> void registerFactory(
            ParticleType<T> type,
            Function<SpriteProvider, ParticleFactory<T>> factoryProvider
    ) {
        ParticleFactoryRegistry.getInstance().register(type, factoryProvider::apply);
    }

    public static <T extends ParticleEffect> void registerSplashFactory(ParticleType<T> type, ParticleFactoryRegistry.PendingParticleFactory<T> factory){
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

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Executor prepareExecutor, Executor applyExecutor) {
        return null;
    }
}
