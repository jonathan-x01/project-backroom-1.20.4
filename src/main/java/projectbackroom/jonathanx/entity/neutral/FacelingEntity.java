package projectbackroom.jonathanx.entity.neutral;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.entity.EntityBehaviourState;

import java.util.EnumSet;
import java.util.Random;
import java.util.UUID;

public class FacelingEntity extends HostileEntity implements Angerable {

    public static final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final TrackedData<String> TYPE = DataTracker.registerData(FacelingEntity.class, TrackedDataHandlerRegistry.STRING);
    private static final TrackedData<Boolean> PROVOKED = DataTracker.registerData(FacelingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> BABY = DataTracker.registerData(FacelingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public FacelingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttribute(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH,30)
                .add(EntityAttributes.MAX_ABSORPTION, 2)
                .add(EntityAttributes.ATTACK_DAMAGE, 2)
                .add(EntityAttributes.MOVEMENT_SPEED,0.2);
    }

    @Override
    protected void initGoals() {
        GoalSelector goal = this.goalSelector;
        goal.add(0, new SwimGoal(this));
        goal.add(1, new AvoidPlayersWhenScaredGoal<>(this, PlayerEntity.class, 6.0f, 1.0, 2.2));
        goal.add(2, new WanderAroundFarGoal(this, 1.0f));
        goal.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6f));
        goal.add(5, new LookAroundGoal(this));

        this.targetSelector.add(2, new AttackPlayersGoal(this, 1.5f));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        this.dataTracker.set(BABY,randomIsChild());
        this.dataTracker.set(TYPE, String.valueOf(getAState()));
        this.dataTracker.set(PROVOKED,false);
    }

    @Override
    public boolean canBeLeashed() {
        return getFacelingState().equals(EntityBehaviourState.FRIENDLY);
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

    @Override
    public boolean isBaby() {
        return this.dataTracker.get(BABY);
    }

    public void addScaredSpeed(){
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,30,3,false,false));
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (this.isBaby()){
            if (getFacelingState().equals(EntityBehaviourState.FRIENDLY)){
                setFacelingState(EntityBehaviourState.SCARED);
            }
            addScaredSpeed();
        } else {
            if (getFacelingState().equals(EntityBehaviourState.FRIENDLY)){
                setFacelingState(EntityBehaviourState.AGGRESSIVE);
                this.getWorld().addParticle(ParticleTypes.ANGRY_VILLAGER,this.getX(),this.getY(),this.getZ(),1,1,1);
            } else if (getFacelingState().equals(EntityBehaviourState.SCARED)){
                addScaredSpeed();
            }
        }
        return super.damage(world, source, amount);
    }

    private static boolean randomIsChild(){
        Random random = new Random();
        return random.nextDouble() < 0.5f;
    }

    private EntityBehaviourState getAState(){
        Random random = new Random();
        double percentage = random.nextDouble();
        if (isBaby()){
            if (percentage < 0.2){
                return EntityBehaviourState.FRIENDLY;
            } else {
                return EntityBehaviourState.SCARED;
            }
        } else {
            if (percentage < 0.2){
                return EntityBehaviourState.FRIENDLY;
            } else if (percentage < 0.8){
                return EntityBehaviourState.SCARED;
            }
            return EntityBehaviourState.AGGRESSIVE;
        }
    }

    public EntityBehaviourState getFacelingState(){
        return EntityBehaviourState.valueOf(this.dataTracker.get(TYPE));
    }

    public void setFacelingState(String type){
        this.dataTracker.set(TYPE, type);
    }

    public void setFacelingState(EntityBehaviourState type){
        this.dataTracker.set(TYPE, String.valueOf(type));
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f, 1);
    }

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            idleAnimationState.start(this.age);
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

    static class AvoidPlayersWhenScaredGoal<T extends LivingEntity>
            extends FleeEntityGoal<T> {
        private final FacelingEntity faceling;

        public AvoidPlayersWhenScaredGoal(FacelingEntity mob, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
            this.faceling = mob;
        }

        @Override
        public boolean canStart() {
            if (super.canStart() && this.targetEntity instanceof PlayerEntity){
                return faceling.getFacelingState().equals(EntityBehaviourState.SCARED);
            }
            return false;
        }
    }

    static class AttackPlayersGoal
            extends Goal
    {
        private final FacelingEntity faceling;
        private final double speed;
        private final double followRange;

        public AttackPlayersGoal(FacelingEntity faceling, double speed){
            this.faceling = faceling;
            this.speed = speed;
            this.followRange = faceling.getAttributeValue(EntityAttributes.FOLLOW_RANGE);
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return this.faceling.getFacelingState().equals(EntityBehaviourState.AGGRESSIVE);
        }

        @Override
        public void start() {
            PlayerEntity targetPlayer = this.faceling.getWorld().getClosestPlayer(faceling, this.followRange);
            if (targetPlayer != null && !targetPlayer.isCreative()) {
                this.faceling.getNavigation().startMovingTo(targetPlayer, this.speed);
            }
        }

        @Override
        public boolean shouldContinue() {
            PlayerEntity targetPlayer = this.faceling.getWorld().getClosestPlayer(this.faceling, this.followRange);
            return targetPlayer != null && !targetPlayer.isCreative() && this.faceling.getFacelingState().equals(EntityBehaviourState.AGGRESSIVE) && this.faceling.squaredDistanceTo(targetPlayer) < this.followRange * this.followRange;
        }

        @Override
        public void stop() {
            this.faceling.getNavigation().stop();
        }

        @Override
        public void tick() {
            PlayerEntity targetPlayer = this.faceling.getWorld().getClosestPlayer(this.faceling, this.followRange);
            if (targetPlayer != null) {
                this.faceling.getLookControl().lookAt(targetPlayer, 30.0F, 30.0F);
                double distanceToPlayer = this.faceling.squaredDistanceTo(targetPlayer);

                // Check if the entity is close enough to attack
                if (distanceToPlayer > this.faceling.getWidth() * this.faceling.getWidth() + 1) { // Ensure the entity gets really close
                    this.faceling.getNavigation().startMovingTo(targetPlayer, this.speed);
                } else {
                    this.faceling.tryAttack(this.getServerWorld(this.faceling),targetPlayer); // Attempt to attack
                    this.faceling.getNavigation().stop();
                }
            } else {
                this.faceling.getNavigation().stop();
            }
        }
    }
}