package projectbackroom.jonathanx.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.block.state.pipeTypes.LargeHorizontalPipeTypes;
import projectbackroom.jonathanx.init.BackroomBlockEntities;

public class PipeBlockEntity extends BlockEntity {
    private Identifier fluidContainer;
    private LargeHorizontalPipeTypes pipeVariety;
    private int varietyID = 0;

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(BackroomBlockEntities.PIPE_BLOCK_ENTITY, pos, state);
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

    public Fluid getFluidContainer(){
        return Registries.FLUID.get(this.fluidContainer);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        if (nbt.contains("fluid_container")){
            this.fluidContainer = Identifier.of(nbt.getString("fluid_container"));
        } else if (nbt.contains("variety")){
            this.pipeVariety = LargeHorizontalPipeTypes.valueOf(nbt.getString("variety"));
        } else if (nbt.contains("variety_id")){
            this.varietyID = nbt.getInt("variety_id");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
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
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        NbtCompound nbt = new NbtCompound();
        writeNbt(nbt, registries);
        return nbt;
    }
}
