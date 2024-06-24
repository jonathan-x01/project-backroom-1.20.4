package projectbackroom.jonathanx.registry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.CorruptedBlock;
import projectbackroom.jonathanx.blocks.entity.CorruptedBlockEntity;

public class ModdedBlockEntities {
    public static final BlockEntityType<CorruptedBlockEntity> CORRUPTED_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, ProjectBackroom.id("corrupted_block_entity"),BlockEntityType.Builder.create(CorruptedBlockEntity::new,ModdedBlocks.CORRUPTED_BLOCK).build());
}
