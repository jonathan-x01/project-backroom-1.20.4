package projectbackroom.jonathanx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.registry.ModdedBlocks;
import projectbackroom.jonathanx.state.property.DestinationProperty;

public class CorruptedBlock extends Block implements BlockEntityProvider {
    public static final DestinationProperty DESTINATION = DestinationProperty.of("destination");

    public CorruptedBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DESTINATION);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient() && entity instanceof ServerPlayerEntity player){
            ServerWorld overworld = player.getServer().getWorld(World.OVERWORLD);
            BlockPos spawnPoint = overworld.getSpawnPos();
            player.moveToWorld(overworld);
            player.teleport(overworld, (double) spawnPoint.getX(), (double) spawnPoint.getY(), (double) spawnPoint.getZ(),player.getYaw(),player.getPitch());
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
