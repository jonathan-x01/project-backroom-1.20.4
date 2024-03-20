package projectbackroom.jonathanx.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.ProjectBackroom;

import java.util.Random;
import java.util.function.ToIntFunction;

public class level_0_led_dropceiling extends Block {
    public static int MIN = 1;
    public static int MAX = 15;
    public static final IntProperty LIGHTING = IntProperty.of("power",MIN,MAX);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHTING);
    }

    public level_0_led_dropceiling(Settings settings) {
        super(settings);
        ProjectBackroom.LOGGER.info("LED Dropceiling init");
        setDefaultState(getDefaultState().with(LIGHTING, 1));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        Random random = new Random();
        int next = random.nextInt((MAX - MIN) + 1) + MIN;
        world.setBlockState(pos, state.with(LIGHTING, next));
        getSettings().luminance((value) -> value.get(LIGHTING));
    }
}
