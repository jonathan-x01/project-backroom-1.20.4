package projectbackroom.jonathanx.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.Random;
import java.util.UUID;

public class FacelingEntity extends HostileEntity implements Angerable {

    public static final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final TrackedData<String> TYPE = DataTracker.registerData(FacelingEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<Boolean> PROVOKED = DataTracker.registerData(FacelingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_CHILD = DataTracker.registerData(FacelingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public enum FacelingType {
        FRIENDLY,
        AGGRESSIVE,
        SCARED
    }

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    public static DefaultAttributeContainer.Builder createFacelingAttribute(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,30)
                .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,1);
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    protected void initGoals() {
        GoalSelector goal = this.goalSelector;
        goal.add(0, new SwimGoal(this));
        goal.add(1, new WanderAroundFarGoal(this,10));
        goal.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        goal.add(3, new LookAroundGoal(this));
    }

    public FacelingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return super.canBeLeashedBy(player);
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int angerTime) {

    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return null;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {

    }

    @Override
    public void chooseRandomAngerTime() {

    }

    private static FacelingType getRandomType(){
        Random random = new Random();
        double percentage = random.nextDouble();
        if (percentage < 0.2){
            return FacelingType.FRIENDLY;
        } else if (percentage < 0.8){
            return FacelingType.SCARED;
        }
        return FacelingType.AGGRESSIVE;
    }

    private static boolean randomIsChild(){
        Random random = new Random();
        return random.nextDouble() < 0.5f;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TYPE, String.valueOf(getRandomType()));
        this.dataTracker.startTracking(PROVOKED,false);
        this.dataTracker.startTracking(IS_CHILD,randomIsChild());
    }
}
