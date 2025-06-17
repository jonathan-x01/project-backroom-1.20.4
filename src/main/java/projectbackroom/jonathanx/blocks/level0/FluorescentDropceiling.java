package projectbackroom.jonathanx.blocks.level0;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.registry.ModSounds;

public class FluorescentDropceiling extends Level0Block {
    public static int LIGHTING_MIN = 5;
    public static int LIGHTING_MAX = 15;
    public static int BUZZING_VOLUME_MIN = 1;
    public static int BUZZING_VOLUME_MAX = 4;

    public static final IntProperty LIGHTING = IntProperty.of("power",LIGHTING_MIN,LIGHTING_MAX);
    public static final IntProperty DEFAULT_LIGHTING = IntProperty.of("default_power",LIGHTING_MIN,LIGHTING_MAX);
    public static final BooleanProperty FLICKERING = BooleanProperty.of("flickering");
    public static final IntProperty BUZZING_VOLUME = IntProperty.of("buzzing_volume",BUZZING_VOLUME_MIN,BUZZING_VOLUME_MAX);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder
                .add(LIGHTING)
                .add(DEFAULT_LIGHTING)
                .add(BUZZING_VOLUME)
                .add(FLICKERING);
    }

    public FluorescentDropceiling(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHTING, 5));
        setDefaultState(getDefaultState().with(DEFAULT_LIGHTING, 5));
        setDefaultState(getDefaultState().with(BUZZING_VOLUME,1));
        setDefaultState(getDefaultState().with(FLICKERING, true));
    }

    public static int getRandomLighting(){
        return Random.create().nextBetween(LIGHTING_MIN, LIGHTING_MAX);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        int randomLighting = getRandomLighting();
        int nextBuzzingVolume = Random.create().nextBetween(BUZZING_VOLUME_MIN, BUZZING_VOLUME_MAX);

        BlockState randomState = state
                .with(LIGHTING, randomLighting)
                .with(DEFAULT_LIGHTING, randomLighting)
                .with(FLICKERING, Random.create().nextBoolean())
                .with(BUZZING_VOLUME, nextBuzzingVolume);

        // Applies the random value to the placed block's block state.
        world.setBlockState(pos, randomState);
        world.playSoundAtBlockCenter(pos, ModSounds.BUZZING_LIGHTS, SoundCategory.AMBIENT, nextBuzzingVolume / 0.25f, 1f, true);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (state.get(FLICKERING)){
            int range = random.nextBetween(0,10);
            if (range < 4){
                world.setBlockState(pos, state.with(LIGHTING, 5));
            } else {
                world.setBlockState(pos, state.with(LIGHTING, state.get(DEFAULT_LIGHTING)));
            }
        }
    }

    @Override
    public boolean shouldBeReplacedByCorruption(){
        return true;
    }
}
