package projectbackroom.jonathanx.blocks.level2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Objects;

public class BiologicalPipeBlock extends Block {
    public BiologicalPipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        ServerWorld serverWorld = Objects.requireNonNull(world.getServer()).getWorld(world.getRegistryKey());
        if (entity instanceof ItemEntity item){
            item.kill(serverWorld);
        } else if (entity instanceof ZombieEntity zombie){
            zombie.damage(serverWorld, world.getDamageSources().magic(), 0.5f);
        }
    }
}
