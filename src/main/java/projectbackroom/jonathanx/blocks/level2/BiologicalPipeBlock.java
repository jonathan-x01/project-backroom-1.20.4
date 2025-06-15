package projectbackroom.jonathanx.blocks.level2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BiologicalPipeBlock extends Block {
    public BiologicalPipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity item){
            item.kill();
        }
        if (entity instanceof ZombieEntity zombie){
            //zombie.damage(new DamageSource(null, zombie), 0.5f);
            zombie.setHealth(zombie.getHealth() - 0.5f);
        }
    }
}
