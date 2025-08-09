package projectbackroom.jonathanx.block.fluids;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import projectbackroom.jonathanx.util.ColorManager;

import java.util.Map;

public class BackroomFluidBlock extends FluidBlock {
    private static final Map<FlowableFluid, BackroomFluidBlock> FLUIDS = Maps.newIdentityHashMap();
    protected final BackroomFluidBlock.Settings settings;
    private final FlowableFluid fluid;

    public BackroomFluidBlock(FlowableFluid fluid, AbstractBlock.Settings settings, BackroomFluidBlock.Settings fluidSettings){
        super(fluid, settings);
        this.settings = fluidSettings;
        this.fluid = fluid;
        FLUIDS.put(fluid, this);
    }

    protected StatusEffectInstance[] applyEffectsToEntities(){
        return new StatusEffectInstance[0];
    }

    public static Iterable<BackroomFluidBlock> getAll(){
        return Iterables.unmodifiableIterable(FLUIDS.values());
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.settings.primaryColor.getHexColor() : 0;
    }

    public ColorManager getColor(){
        return this.settings.primaryColor;
    }

    public FlowableFluid getFluid(){
        return this.fluid;
    }

    private void setEffectsOnEntities(StatusEffectInstance[] effects, LivingEntity living){
        for (StatusEffectInstance effect : effects){
            living.addStatusEffect(effect);
        }
    }

    public BackroomFluidBlock.Settings getFluidSettings(){
        return this.settings;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (entity instanceof LivingEntity living){
            if (entity instanceof PlayerEntity player){
                if (!player.isCreative()){
                    setEffectsOnEntities(applyEffectsToEntities(), living);
                }
            } else {
                setEffectsOnEntities(applyEffectsToEntities(), living);
            }
        }
    }

    public static class Settings {
        boolean useDefaultFluidPhysics;
        ColorManager primaryColor;
        float fogStart;
        float fogEnd;
        float fogAlpha;
        Vec3d drag;
        float speed;
        FluidSplashParticleManager splashParticleManager = null;

        private Settings(){
            this.useDefaultFluidPhysics = true;
            this.fogStart = -8.0F;
            this.fogEnd = 25.0F;
            this.fogAlpha = 0.5F;
        }

        public static Settings create(){
            return new Settings();
        }

        public Settings useDefaultPhysics(boolean useDefault){
            this.useDefaultFluidPhysics = useDefault;
            return this;
        }

        public boolean usesDefaultPhysics(){
            return this.useDefaultFluidPhysics;
        }

        public Settings setDrag(Vec3d drag){
            this.drag = drag;
            this.useDefaultPhysics(false);
            return this;
        }

        public Vec3d getDrag(){
            return this.drag;
        }

        public Settings setColor(int hex){
            this.primaryColor = new ColorManager(hex);
            return this;
        }

        public ColorManager getColor(){
            return this.primaryColor;
        }

        public Settings setFogStart(float fogStart){
            this.fogStart = fogStart;
            return this;
        }

        public float getFogStart(){
            return this.fogStart;
        }

        public Settings setFogEnd(float fogEnd){
            this.fogEnd = fogEnd;
            return this;
        }

        public float getFogEnd(){
            return this.fogEnd;
        }

        public Settings setFogAlpha(float alpha){
            this.fogAlpha = alpha;
            return this;
        }

        public float getFogAlpha(){
            return this.fogAlpha;
        }

        public Settings setSpeed(float speed){
            this.speed = speed;
            this.useDefaultPhysics(false);
            return this;
        }

        public float getSpeed(){
            return this.speed;
        }

        public Settings setSplashParticles(FluidSplashParticleManager splashParticleManager){
            this.splashParticleManager = splashParticleManager;
            return this;
        }

        public FluidSplashParticleManager getSplashParticles(){
            return this.splashParticleManager;
        }
    }

    public static class FluidSplashParticleManager {
        SimpleParticleType splashParticle;
        SimpleParticleType bubbleParticle;

        private FluidSplashParticleManager(SimpleParticleType splashParticle, SimpleParticleType bubbleParticle){
            this.splashParticle = splashParticle;
            this.bubbleParticle = bubbleParticle;
        }

        public static FluidSplashParticleManager create(){
            return new FluidSplashParticleManager(ParticleTypes.SPLASH, ParticleTypes.BUBBLE);
        }

        public FluidSplashParticleManager removeDefaultSplashParticle(){
            this.splashParticle = null;
            return this;
        }

        public FluidSplashParticleManager removeDefaultBubbleParticle(){
            this.bubbleParticle = null;
            return this;
        }

        public FluidSplashParticleManager removeDefaultParticles(){
            this.removeDefaultSplashParticle().removeDefaultBubbleParticle();
            return this;
        }

        public FluidSplashParticleManager setParticles(SimpleParticleType splashParticle, SimpleParticleType bubbleParticle){
            this.splashParticle = splashParticle;
            this.bubbleParticle = bubbleParticle;
            return this;
        }

        public SimpleParticleType  getSplashParticle(){
            return this.splashParticle;
        }

        public SimpleParticleType getBubbleParticle(){
            return this.bubbleParticle;
        }
    }
}
