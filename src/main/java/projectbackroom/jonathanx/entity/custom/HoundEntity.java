package projectbackroom.jonathanx.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class HoundEntity extends HostileEntity {
    public static final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public HoundEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttribute(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,30)
                .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.4);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    public void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void initGoals() {
        GoalSelector goal = this.goalSelector;
        goal.add(0, new MeleeAttackGoal(this, 1.0f, true));
        goal.add(1, new EscapeSunlightGoal(this, 0.6f));
        goal.add(2, new LookAtEntityGoal(this, LivingEntity.class, 4f));
        goal.add(3, new WanderAroundGoal(this, 0.4f));
        this.initTargetGoals();
    }

    protected void initTargetGoals(){
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, LivingEntity.class, true));
    }
}
