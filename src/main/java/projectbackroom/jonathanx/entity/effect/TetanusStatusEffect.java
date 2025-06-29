package projectbackroom.jonathanx.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.concurrent.ThreadLocalRandom;

public class TetanusStatusEffect extends StatusEffect {
    public TetanusStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x192151);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        int tickRandom = ThreadLocalRandom.current().nextInt(10, 30);
        if (entity.age % tickRandom != 0) return;

        int percentage = ThreadLocalRandom.current().nextInt(0, 101);
        if (percentage < 10){
            entity.damage(entity.getDamageSources().magic(), 2);
        } else if (percentage < 30){
            if (entity.isOnGround()){
                entity.setVelocity(entity.getVelocity().add(0, 0.42D, 0));
                entity.velocityModified = true;
            }
        } else if (percentage < 60){
            if (!entity.handSwinging){
                entity.swingHand(Hand.MAIN_HAND);
            }
        } else if (percentage < 70){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, tickRandom, amplifier, false, false));
        } else if (percentage < 80){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, tickRandom, amplifier, false, false));
        }
    }
}
