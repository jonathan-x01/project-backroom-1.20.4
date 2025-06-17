package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.DeathmothEntity;
import projectbackroom.jonathanx.entity.neutral.FacelingEntity;
import projectbackroom.jonathanx.entity.hostile.HoundEntity;
import projectbackroom.jonathanx.entity.hostile.SmilerEntity;

public class ModEntities {

    public static EntityType<FacelingEntity> FACELINGS = Registry.register(Registries.ENTITY_TYPE,
            ProjectBackroom.id("faceling"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FacelingEntity::new).dimensions(EntityDimensions.fixed(1f,2f)).build());

    public static EntityType<SmilerEntity> SMILERS = Registry.register(Registries.ENTITY_TYPE,
            ProjectBackroom.id("smilers"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SmilerEntity::new).dimensions(EntityDimensions.fixed(1f,2f)).build());

    public static EntityType<HoundEntity> HOUND = Registry.register(Registries.ENTITY_TYPE,
            ProjectBackroom.id("hound"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HoundEntity::new).dimensions(EntityDimensions.fixed(2f,1f)).build());

    public static EntityType<DeathmothEntity> DEATHMOTH = Registry.register(Registries.ENTITY_TYPE,
            ProjectBackroom.id("deathmoth"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DeathmothEntity::new).dimensions(EntityDimensions.fixed(1f,2f)).build());

    public static void registerModdedEntities(){
        ProjectBackroom.displayRegisteredSectors(ModEntities.class);

        FabricDefaultAttributeRegistry.register(ModEntities.FACELINGS, FacelingEntity.createAttribute());

        FabricDefaultAttributeRegistry.register(ModEntities.SMILERS, SmilerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.HOUND, HoundEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(ModEntities.DEATHMOTH, DeathmothEntity.createHostileAttributes());
    }
}
