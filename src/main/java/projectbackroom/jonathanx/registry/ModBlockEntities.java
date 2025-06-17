package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.entity.CorruptedBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<CorruptedBlockEntity> CORRUPTED_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, ProjectBackroom.id("corrupted_block_entity"),
                    FabricBlockEntityTypeBuilder.create(CorruptedBlockEntity::new, ModBlocks.CORRUPTED_BLOCK).build());

    public static void initalize(){
        ProjectBackroom.displayRegisteredSectors(ModBlockEntities.class);
    }
}
