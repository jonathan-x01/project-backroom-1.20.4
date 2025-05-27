package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.CorruptedBlock;
import projectbackroom.jonathanx.blocks.entity.CorruptedBlockEntity;

public class ModdedBlockEntities {
    public static final BlockEntityType<CorruptedBlockEntity> CORRUPTED_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, ProjectBackroom.id("corrupted_block_entity"),
                    FabricBlockEntityTypeBuilder.create(CorruptedBlockEntity::new,ModdedBlocks.CORRUPTED_BLOCK).build());

    public static void initalize(){
        ProjectBackroom.displayRegisteredSectors(ModdedBlockEntities.class);
    }
}
