package projectbackroom.jonathanx.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class BackroomPipeLeakParticle extends BlockLeakParticle {
    public static final List<float[]> contaminatedWaterColors = new ArrayList<>(List.of(
            new float[] {0.384f, 0.408f, 0.275f}, // Murky Green-Brown
            new float[] {0.659f, 0.353f, 0.173f}, // Rusty Orange
            new float[] {0.235f, 0.255f, 0.267f}, // Dark Grey
            new float[] {0.706f, 0.698f, 0.675f}, // Cloudy White-Grey
            new float[] {0.569f, 0.588f, 0.255f}, // Sickly Yellow-Green
            new float[] {0.157f, 0.235f, 0.412f}  // Oil-Slick Blue
    ));
    public static float[] chosenCWColors;
    protected BackroomPipeLeakParticle(ClientWorld world, double x, double y, double z, Fluid fluid) {
        super(world, x, y, z, fluid);
        this.gravityStrength = 0.06f;
    }

    public static void chooseRandomContaminatedWaterColors(){
        ProjectBackroom.LOGGER.info(String.valueOf(contaminatedWaterColors.size()));
        Random random = new Random();
        int chosen = random.nextInt(0, contaminatedWaterColors.size());
        chosenCWColors = contaminatedWaterColors.get(chosen);
    }

    public static SpriteBillboardParticle createDrippingAlmondWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        BlockLeakParticle particle = new Dripping(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.FALLING_ALMOND_WATER);
        particle.setColor(0.933333F, 0.86F, 0.77F);
        return particle;
    }

    public static SpriteBillboardParticle createFallingAlmondWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        BlockLeakParticle particle = new ContinuousFalling(world, x, y, z, Fluids.EMPTY, ParticleTypes.SPLASH);
        particle.setColor(0.933333F, 0.86F, 0.77F);
        return particle;
    }

    public static SpriteBillboardParticle createDrippingBlackSludge(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        Dripping dripping = new Dripping(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.FALLING_BLACK_SLUDGE);
        dripping.gravityStrength *= 0.01f;
        dripping.maxAge = 100;
        dripping.setColor(0f,0f,0f);
        return dripping;
    }

    public static SpriteBillboardParticle createFallingBlackSludge(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle blockLeakParticle = new FallingHoney(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.LANDING_BLACK_SLUDGE);
        blockLeakParticle.gravityStrength *= 0.01f;
        blockLeakParticle.setColor(0f,0f,0f);
        return blockLeakParticle;
    }

    public static SpriteBillboardParticle createLandingBlackSludge(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        BlockLeakParticle blockLeakParticle = new Landing(world, x, y, z, Fluids.EMPTY);
        blockLeakParticle.maxAge = (int)(128.0 / (Math.random() * 0.8 + 0.2));
        blockLeakParticle.setColor(0f, 0f, 0f);
        return blockLeakParticle;
    }

    public static SpriteBillboardParticle createDrippingContaminatedWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle particle = new Dripping(world, x, y, z, Fluids.EMPTY, BackroomParticleTypes.FALLING_CONTAMINATED_WATER);
        ProjectBackroom.LOGGER.info("Dripping Contaminated Water");
        chooseRandomContaminatedWaterColors();
        ProjectBackroom.LOGGER.info(String.valueOf(chosenCWColors[0]));
        ProjectBackroom.LOGGER.info(String.valueOf(chosenCWColors[1]));
        ProjectBackroom.LOGGER.info(String.valueOf(chosenCWColors[2]));
        particle.setColor(chosenCWColors[0], chosenCWColors[1], chosenCWColors[2]);
        return particle;
    }

    public static SpriteBillboardParticle createFallingContaminatedWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle particle = new ContinuousFalling(world, x, y, z, Fluids.EMPTY, ParticleTypes.SPLASH);
        particle.setColor(chosenCWColors[0], chosenCWColors[1], chosenCWColors[2]);
        return particle;
    }

    public static SpriteBillboardParticle createLandingContaminatedWater(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        BlockLeakParticle blockLeakParticle = new ContinuousFalling(world, x, y, z, Fluids.EMPTY, ParticleTypes.SPLASH);
        return blockLeakParticle;
    }
}
