package projectbackroom.jonathanx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.blocks.entity.CorruptedBlockEntity;
import projectbackroom.jonathanx.state.property.DestinationProperty;

import java.util.EnumSet;
import java.util.Set;

public class CorruptedBlock extends Block implements BlockEntityProvider {
    public static final DestinationProperty DESTINATION = DestinationProperty.of("destination");

    public CorruptedBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        //builder.add(DESTINATION);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient() && entity instanceof ServerPlayerEntity player){
            ServerWorld overworld = player.getServer().getWorld(World.OVERWORLD);
            if (overworld != null && player.getWorld() != overworld){
                BlockPos spawnPoint = overworld.getSpawnPos();
                Set<PositionFlag> flags = EnumSet.noneOf(PositionFlag.class);
                player.teleport(overworld, spawnPoint.getX() + 0.5, spawnPoint.getY() + 0.5, spawnPoint.getZ() + 0.5, flags, player.getYaw(), player.getPitch(), false);
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        BlockEntity current = world.getBlockEntity(pos);
        if (current instanceof CorruptedBlockEntity corruptedBlockEntity){
            Identifier previousBlockKey = corruptedBlockEntity.getPreviousBlock();
            if (previousBlockKey != null){
                Block previousBlock = Registries.BLOCK.get(previousBlockKey);
                world.setBlockState(pos, previousBlock.getDefaultState());
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        randomTick(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CorruptedBlockEntity(pos, state);
    }
}
