package projectbackroom.jonathanx.blocks.level0Blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.registry.ModdedSounds;

import java.util.Random;

public class FluorescentDropceiling extends Level0Block {
    public static int LIGHTING_MIN = 5;
    public static int LIGHTING_MAX = 15;
    public static int BUZZING_VOLUME_MIN = 1;
    public static int BUZZING_VOLUME_MAX = 4;

    public static final IntProperty LIGHTING = IntProperty.of("power",LIGHTING_MIN,LIGHTING_MAX);
    public static final BooleanProperty BUZZING = BooleanProperty.of("buzzing");
    public static final IntProperty BUZZING_VOLUME = IntProperty.of("buzzing_volume",BUZZING_VOLUME_MIN,BUZZING_VOLUME_MAX);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHTING).add(BUZZING_VOLUME);
    }

    public FluorescentDropceiling(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHTING, 5));
        setDefaultState(getDefaultState().with(BUZZING_VOLUME,1));
    }

    public static int getRandomLighting(){
        /// Applies a random value to lighting
        // Creates a new random object for lighting
        Random randomLighting = new Random();
        // Sets the random range between min and max.
        return randomLighting.nextInt((LIGHTING_MAX - LIGHTING_MIN) + 1) + LIGHTING_MIN;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        // This will get a random lighting value;
        int nextLighting = getRandomLighting();

        // Applies the random value to the placed block's block state.
        world.setBlockState(pos, state.with(LIGHTING, nextLighting));

        /// Applies a random value to buzzing volume
        Random randomBuzzingVolume = new Random();
        int nextBuzzingVolume = randomBuzzingVolume.nextInt((BUZZING_VOLUME_MAX - BUZZING_VOLUME_MIN) + 1) + BUZZING_VOLUME_MIN;
        world.setBlockState(pos, state.with(BUZZING_VOLUME, nextBuzzingVolume));
        world.playSound(pos.getX(),pos.getY(),pos.getZ(), ModdedSounds.BUZZING_LIGHTS, SoundCategory.AMBIENT,nextBuzzingVolume / 0.25f,1f,true);
    }
}
