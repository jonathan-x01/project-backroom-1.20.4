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
import projectbackroom.jonathanx.blocks.state.pipeTypes.LargeHorizontalPipeTypes;

public class PipeBlockEntity extends BlockEntity {
    private Identifier fluidContainer;
    private LargeHorizontalPipeTypes pipeVariety;
    private int varietyID = 0;

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(BackroomBlockEntities.PIPE_BLOCK_ENTITY, pos, state);
    }

    public void setVarietyID(int id){
        this.varietyID = id;
    }

    public void setPipeVariety(LargeHorizontalPipeTypes pipeVariety){
        this.pipeVariety = pipeVariety;
    }

    public void setFluidContainer(Fluid fluid){
        this.fluidContainer = Registries.FLUID.getId(fluid);
        markDirty();
        if (this.world != null && !this.world.isClient) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    public int getVarietyID(){
        return this.varietyID;
    }

    public LargeHorizontalPipeTypes getPipeVariety(){
        return this.pipeVariety;
    }

    public Fluid getFluidContainer(){
        return Registries.FLUID.get(this.fluidContainer);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("fluid_container")){
            this.fluidContainer = new Identifier(nbt.getString("fluid_container"));
        } else if (nbt.contains("variety")){
            this.pipeVariety = LargeHorizontalPipeTypes.valueOf(nbt.getString("variety"));
        } else if (nbt.contains("variety_id")){
            this.varietyID = nbt.getInt("variety_id");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.fluidContainer != null){
            nbt.putString("fluid_container",this.fluidContainer.toString());
        } else if (this.pipeVariety != null){
            nbt.putString("variety", this.pipeVariety.asString());
        } else if (this.varietyID != 0){
            nbt.putInt("variety_id", this.varietyID);
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
