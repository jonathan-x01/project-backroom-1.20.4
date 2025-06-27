package projectbackroom.jonathanx.blocks.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class PipeBlockEntity extends BlockEntity {
    public Identifier fluidContainer;
    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(BackroomBlockEntities.PIPE_BLOCK_ENTITY, pos, state);
    }

    public void setFluidContainer(Fluid fluid){
        this.fluidContainer = Registries.FLUID.getId(fluid);
        markDirty();
        if (this.world != null && !this.world.isClient) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    public Fluid getFluidContainer(){
        return Registries.FLUID.get(this.fluidContainer);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("fluid_container")){
            this.fluidContainer = new Identifier(nbt.getString("fluid_container"));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.fluidContainer != null){
            nbt.putString("fluid_container",this.fluidContainer.toString());
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = new NbtCompound();
        writeNbt(nbt);
        return nbt;
    }
}
