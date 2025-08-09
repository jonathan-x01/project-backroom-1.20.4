package projectbackroom.jonathanx.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import projectbackroom.jonathanx.init.BackroomBlockEntities;

public class CorruptedBlockEntity extends BlockEntity {
    private Identifier previousBlock;
    public CorruptedBlockEntity(BlockPos pos, BlockState state) {
        super(BackroomBlockEntities.CORRUPTED_BLOCK_ENTITY, pos, state);
    }

    public Identifier getPreviousBlock(){
        return this.previousBlock;
    }

    public void setPreviousBlock(Identifier previousBlock){
        this.previousBlock = previousBlock;
        markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        if (nbt.contains("Previous")) {
            this.previousBlock = Identifier.of(nbt.getString("previous_block"));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        if (this.previousBlock != null){
            nbt.putString("previous_block",this.previousBlock.toString());
        }
    }
}
