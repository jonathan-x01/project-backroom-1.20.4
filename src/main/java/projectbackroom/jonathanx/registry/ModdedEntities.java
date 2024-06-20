package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.custom.FacelingEntity;
import projectbackroom.jonathanx.entity.custom.SmilerEntity;

public class ModdedEntities {

    public static EntityType<FacelingEntity> FACELINGS = Registry.register(Registries.ENTITY_TYPE,
            ProjectBackroom.id("faceling"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FacelingEntity::new).dimensions(EntityDimensions.fixed(1f,2f)).build());

    public static EntityType<SmilerEntity> SMILERS = Registry.register(Registries.ENTITY_TYPE,
            ProjectBackroom.id("smilers"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SmilerEntity::new).dimensions(EntityDimensions.fixed(1f,2f)).build());

    public static void registerModdedEntities(){
        ProjectBackroom.displayRegisteredSectors(ModdedEntities.class);
        FabricDefaultAttributeRegistry.register(ModdedEntities.FACELINGS, FacelingEntity.createFacelingAttribute());
        FabricDefaultAttributeRegistry.register(ModdedEntities.SMILERS, SmilerEntity.createAttributes());
    }
}
