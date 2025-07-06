package projectbackroom.jonathanx.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import projectbackroom.jonathanx.particle.util.ParticleColorManagement;

@Environment(EnvType.CLIENT)
public class BackroomPipeLeakParticle extends BlockLeakParticle {
    public static float[] chosenCWColors;
    protected BackroomPipeLeakParticle(ClientWorld world, double x, double y, double z, Fluid fluid) {
        super(world, x, y, z, fluid);
        this.gravityStrength = 0.06f;
    }

    public static SpriteBillboardParticle createDrippingAlmondWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        BlockLeakParticle particle = new Dripping(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.FALLING_ALMOND_WATER);
        particle.setColor(ParticleColorManagement.ALMOND_WATER[0], ParticleColorManagement.ALMOND_WATER[1], ParticleColorManagement.ALMOND_WATER[2]);
        return particle;
    }

    public static SpriteBillboardParticle createFallingAlmondWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        BlockLeakParticle particle = new ContinuousFalling(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.LANDING_ALMOND_WATER);
        particle.setColor(ParticleColorManagement.ALMOND_WATER[0], ParticleColorManagement.ALMOND_WATER[1], ParticleColorManagement.ALMOND_WATER[2]);
        return particle;
    }

    public static SpriteBillboardParticle createLandingAlmondWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        SpriteBillboardParticle particle = new WaterSplashParticle(world, x, y, z, velocityX, velocityY, velocityZ);
        particle.setColor(ParticleColorManagement.ALMOND_WATER[0], ParticleColorManagement.ALMOND_WATER[1], ParticleColorManagement.ALMOND_WATER[2]);
        return particle;
    }

    public static SpriteBillboardParticle createDrippingBlackSludge(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        Dripping dripping = new Dripping(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.FALLING_BLACK_SLUDGE);
        dripping.gravityStrength *= 0.01f;
        dripping.maxAge = 100;
        dripping.setColor(ParticleColorManagement.BLACK_SLUDGE[0], ParticleColorManagement.BLACK_SLUDGE[1], ParticleColorManagement.BLACK_SLUDGE[2]);
        return dripping;
    }

    public static SpriteBillboardParticle createFallingBlackSludge(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle blockLeakParticle = new FallingHoney(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.LANDING_BLACK_SLUDGE);
        blockLeakParticle.gravityStrength *= 0.01f;
        blockLeakParticle.setColor(ParticleColorManagement.BLACK_SLUDGE[0], ParticleColorManagement.BLACK_SLUDGE[1], ParticleColorManagement.BLACK_SLUDGE[2]);
        return blockLeakParticle;
    }

    public static SpriteBillboardParticle createLandingBlackSludge(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        BlockLeakParticle blockLeakParticle = new Landing(world, x, y, z, Fluids.EMPTY);
        blockLeakParticle.maxAge = (int)(128.0 / (Math.random() * 0.8 + 0.2));
        blockLeakParticle.setColor(ParticleColorManagement.BLACK_SLUDGE[0], ParticleColorManagement.BLACK_SLUDGE[1], ParticleColorManagement.BLACK_SLUDGE[2]);
        return blockLeakParticle;
    }

    public static SpriteBillboardParticle createDrippingContaminatedWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle particle = new Dripping(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.FALLING_CONTAMINATED_WATER);
        chosenCWColors = ParticleColorManagement.chooseRandomContaminatedWaterColors();
        particle.setColor(chosenCWColors[0], chosenCWColors[1], chosenCWColors[2]);
        return particle;
    }

    public static SpriteBillboardParticle createFallingContaminatedWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle particle = new ContinuousFalling(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.LANDING_CONTAMINATED_WATER);
        particle.setColor(chosenCWColors[0], chosenCWColors[1], chosenCWColors[2]);
        return particle;
    }

    public static SpriteBillboardParticle createLandingContaminatedWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        SpriteBillboardParticle particle = new WaterSplashParticle(world, x, y, z, velocityX, velocityY, velocityZ);
        particle.setColor(chosenCWColors[0], chosenCWColors[1], chosenCWColors[2]);
        return particle;
    }
}
