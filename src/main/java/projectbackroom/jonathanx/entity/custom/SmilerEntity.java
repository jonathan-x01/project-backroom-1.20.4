package projectbackroom.jonathanx.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class SmilerEntity extends HostileEntity {
    public SmilerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,999999999)
                .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 999999999)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 999999999)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1f, true));
        this.goalSelector.add(1, new AvoidSunlightGoal(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class,30f));
        this.goalSelector.add(6, new WanderAroundFarGoal(this,1.0f));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().getLightLevel(new BlockPos((int) this.getX(),(int) this.getY(),(int) this.getZ())) > 1){
            this.setHealth(0f);
        } else {
            this.heal(10f);
        }
    }
}
