package projectbackroom.jonathanx.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.ArrayList;
import java.util.List;

public class SmilerEntity extends HostileEntity {

    public SmilerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,50)
                .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.6f);
    }

    @Override
    protected void initGoals() {
        GoalSelector goal = this.goalSelector;
        goal.add(0, new MeleeAttackGoal(this, 0.6f, true));
        goal.add(1, new DestroyLightSourceGoal(this, 0.7f, 45, 10));
        goal.add(3, new WanderAroundGoal(this,0.6f));
        goal.add(5, new BreakDoorGoal(this, difficulty -> true));
        goal.add(6, new WanderAroundFarGoal(this,0.3f));

        this.targetSelector.add(0, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, VillagerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        boolean bl = this.isAffectedByDaylight();
        if (bl){
            this.setHealth(0f);
        } else {
            this.heal(2f);
        }
    }

    static class DestroyLightSourceGoal
            extends MoveToTargetPosGoal
    {
        private final MobEntity mob;
        private final int range;
        private final int innerRange;
        private final double speed;
        private final List<Block> targetBlocks = new ArrayList<Block>();
        private static BlockPos target;
        private BlockPos moveToPos;

        public DestroyLightSourceGoal(PathAwareEntity mob, double speed, int range, int innerRange) {
            super(mob, speed, range);
            this.mob = mob;
            this.range = range;
            this.speed = speed;
            this.innerRange = innerRange;

            targetBlocks.add(Blocks.TORCH);
            targetBlocks.add(Blocks.WALL_TORCH);
            targetBlocks.add(Blocks.LANTERN);
            targetBlocks.add(Blocks.GLOWSTONE);
            targetBlocks.add(Blocks.SEA_LANTERN);
            targetBlocks.add(Blocks.CAMPFIRE);
            targetBlocks.add(Blocks.JACK_O_LANTERN);
            targetBlocks.add(Blocks.REDSTONE_TORCH);
        }

        @Override
        public boolean canStart() {
            World world = this.mob.getWorld();
            if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)){
                if (this.foundLightBlock(this.range)){
                    ProjectBackroom.debug(this.moveToPos);
                    Path path = this.mob.getNavigation().findPathTo(this.moveToPos, this.range);
                    if (path != null && world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) && this.mob.getNavigation().startMovingAlong(path, this.speed)){
                        return true;
                    }
                }
            }
            this.mob.getNavigation().stop();
            return false;
        }

        @Override
        public boolean canStop() {
            if (this.targetPos == null) return true;

            BlockState state = this.mob.getWorld().getBlockState(this.targetPos);
            return !targetBlocks.contains(state.getBlock());
        }

        @Override
        public void tick() {
            super.tick();
            if (isInInnerRange()) {
                mob.getWorld().breakBlock(targetPos, false, mob);
            } else {
                super.startMovingToTarget();
            }
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            BlockState state = world.getBlockState(pos);
            boolean isLight = targetBlocks.stream().anyMatch(state::isOf);
            //ProjectBackroom.debug("Checking pos: " + pos + " isLightSource: " + isLight);
            return isLight;
        }

        @Override
        protected void startMovingToTarget() {

        }

        private boolean isInInnerRange() {
            return this.mob.getBlockPos().getSquaredDistance(this.targetPos) <= (this.innerRange * this.innerRange);
        }

        private boolean foundLightBlock(int radius) {
            BlockPos mobPos = mob.getBlockPos();
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos pos = mobPos.add(x, y, z);
                        BlockState state = mob.getWorld().getBlockState(pos);
                        if (targetBlocks.stream().anyMatch(state::isOf)) {
                            //ProjectBackroom.debug("Found light block at: " + pos);
                            this.targetPos = pos;
                            this.moveToPos = findNearbyWalkable(pos);
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private BlockPos findNearbyWalkable(BlockPos lightPos) {
            BlockPos bestPos = null;
            double closestDistance = Double.MAX_VALUE;

            for (Direction direction : Direction.values()) {
                BlockPos adjacent = lightPos.offset(direction);

                // Check if block below is solid and this block is walkable
                BlockPos below = adjacent.down();
                if (mob.getWorld().getBlockState(below).isOpaque() &&
                        mob.getWorld().getBlockState(adjacent).isAir()) {

                    double distance = mob.getBlockPos().getSquaredDistance(adjacent);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        bestPos = adjacent;
                    }
                }
            }

            return bestPos != null ? bestPos : lightPos; // Fallback to original if no good option
        }
    }
}
