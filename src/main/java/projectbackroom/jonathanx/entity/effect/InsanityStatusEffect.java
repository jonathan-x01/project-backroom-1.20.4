package projectbackroom.jonathanx.entity.effect;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.Random;

public class InsanityStatusEffect extends StatusEffect {
    protected InsanityStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x192151);
    }

    public void playWalkingSounds(PlayerEntity entity){
        World world = entity.getWorld();
        BlockPos pos = entity.getBlockPos().down(1);
        BlockState blockState = world.getBlockState(pos);
        Block block = world.getBlockState(pos).getBlock();
        SoundEvent blockStep = block.getSoundGroup(blockState).getStepSound();
        entity.playSound(blockStep, SoundCategory.AMBIENT, 0.5f,1);
    }

    public void playRandomMobSounds(PlayerEntity entity){
        SoundEvent creeper = SoundEvents.ENTITY_CREEPER_PRIMED;
        SoundEvent zombie = SoundEvents.ENTITY_ZOMBIE_AMBIENT;
        Random random = new Random();
        double number = random.nextDouble();
        if (number < 0.5){
            entity.playSound(creeper,SoundCategory.AMBIENT,1,1);
        } else {
            entity.playSound(zombie,SoundCategory.AMBIENT,1,1);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (entity instanceof PlayerEntity){
            Random random = new Random();
            double percentage = random.nextDouble();
            if (percentage < 0.001){
                playWalkingSounds((PlayerEntity) entity);
            } else if (percentage < 0.005){
                playRandomMobSounds((PlayerEntity) entity);
            }
        }
    }
}
