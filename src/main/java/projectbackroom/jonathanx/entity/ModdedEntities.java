package projectbackroom.jonathanx.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.custom.FacelingEntity;

public class ModdedEntities {

    public static EntityType<FacelingEntity> FACELINGS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ProjectBackroom.MOD_ID, "faceling"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FacelingEntity::new).dimensions(EntityDimensions.fixed(1f,2f)).build());

    public static void registerModdedEntities(){
        ProjectBackroom.LOGGER.info("Registering modded entities");
        FabricDefaultAttributeRegistry.register(ModdedEntities.FACELINGS, FacelingEntity.createFacelingAttribute());
    }
}
