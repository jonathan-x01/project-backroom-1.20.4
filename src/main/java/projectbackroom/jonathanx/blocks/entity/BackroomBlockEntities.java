package projectbackroom.jonathanx.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.util.DebugLogger;

public class BackroomBlockEntities {
    public static final BlockEntityType<CorruptedBlockEntity> CORRUPTED_BLOCK_ENTITY = register(
            "corrupted_block_entity",
            FabricBlockEntityTypeBuilder.create(CorruptedBlockEntity::new, BackroomBlocks.CORRUPTED_BLOCK).build()
    );

    public static final BlockEntityType<PipeBlockEntity> PIPE_BLOCK_ENTITY = register(
            "pipe_block_entity",
            FabricBlockEntityTypeBuilder.create(PipeBlockEntity::new, BackroomBlocks.pipeBlocks.toArray(new Block[0])).build()
    );

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> entry){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, ProjectBackroom.id(name), entry);
    }

    public static void initialize(){
        DebugLogger.displayRegisteredSectors(BackroomBlockEntities.class);
    }
}
