package projectbackroom.jonathanx.blocks.level0Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.CorruptedBlock;
import projectbackroom.jonathanx.blocks.entity.CorruptedBlockEntity;
import projectbackroom.jonathanx.registry.ModdedBlockEntities;
import projectbackroom.jonathanx.registry.ModdedBlocks;
import projectbackroom.jonathanx.state.util.Destination;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level0Block extends Block {

    protected static List<Destination> storedDestinations = new ArrayList<Destination>();

    public Level0Block(Settings settings) {
        super(settings);
    }

    private Destination getRandomDestination(){
        if (storedDestinations.size() >= 1){
            int random = new Random().nextInt(storedDestinations.size());
            return storedDestinations.get(random);
        }
        return storedDestinations.get(0);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!player.isCreative()){
            world.setBlockState(pos, ModdedBlocks.CORRUPTED_BLOCK.getDefaultState().with(CorruptedBlock.DESTINATION,getRandomDestination()));
            BlockEntity newBlockEntity = world.getBlockEntity(pos);
            if (newBlockEntity instanceof CorruptedBlockEntity corruptedBlockEntity){
                corruptedBlockEntity.setPreviousBlock(Registries.BLOCK.getId(state.getBlock()));
            }
        }
    }
}
