package projectbackroom.jonathanx.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.registry.ModdedBlockEntities;

public class CorruptedBlockEntity extends BlockEntity {
    private Identifier previousBlock;
    public CorruptedBlockEntity(BlockPos pos, BlockState state) {
        super(ModdedBlockEntities.CORRUPTED_BLOCK_ENTITY, pos, state);
    }

    public Identifier getPreviousBlock(){
        return this.previousBlock;
    }

    public void setPreviousBlock(Identifier previousBlock){
        this.previousBlock = previousBlock;
        markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("Previous")) {
            this.previousBlock = new Identifier(nbt.getString("previous_block"));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.previousBlock != null){
            nbt.putString("previous_block",this.previousBlock.toString());
        }
    }
}
