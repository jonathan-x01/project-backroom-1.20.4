package projectbackroom.jonathanx.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import projectbackroom.jonathanx.particle.util.ParticleColorManagement;

public class Bubble {
    public static class AlmondWaterBubbleFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public AlmondWaterBubbleFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            WaterBubbleParticle particle = new WaterBubbleParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            particle.setColor(ParticleColorManagement.ALMOND_WATER[0], ParticleColorManagement.ALMOND_WATER[1], ParticleColorManagement.ALMOND_WATER[2]);
            particle.setSprite(spriteProvider);
            return particle;
        }
    }

    public static class ContaminatedWaterBubbleFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public ContaminatedWaterBubbleFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            WaterBubbleParticle particle = new WaterBubbleParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            float[] color = ParticleColorManagement.chooseRandomContaminatedWaterColors();
            particle.setColor(color[0], color[1], color[2]);
            particle.setSprite(spriteProvider);
            return particle;
        }
    }
}
