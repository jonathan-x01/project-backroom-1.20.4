package projectbackroom.jonathanx.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import projectbackroom.jonathanx.particle.util.ParticleColorManagement;

public class ContaminatedWaterSplash {
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            WaterSplashParticle particle = new WaterSplashParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            float[] color = ParticleColorManagement.chooseRandomContaminatedWaterColors();
            particle.setColor(color[0], color[1], color[2]);
            particle.setSprite(spriteProvider);
            return particle;
        }
    }
}
