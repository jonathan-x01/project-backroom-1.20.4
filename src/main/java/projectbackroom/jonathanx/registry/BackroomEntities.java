package projectbackroom.jonathanx.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
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
import projectbackroom.jonathanx.entity.projectile.RustyTippedArrowEntity;

public class BackroomEntities {

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

    // projectile
    public static EntityType<RustyTippedArrowEntity> RUSTY_TIPPED_ARROW = register("rusty_tipped_arrow", FabricEntityTypeBuilder.<RustyTippedArrowEntity>create(SpawnGroup.MISC, RustyTippedArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeBlocks(4).trackedUpdateRate(20).build());

    public static void registerModdedEntities(){
        ProjectBackroom.displayRegisteredSectors(BackroomEntities.class);

        FabricDefaultAttributeRegistry.register(BackroomEntities.FACELINGS, FacelingEntity.createAttribute());

        FabricDefaultAttributeRegistry.register(BackroomEntities.SMILERS, SmilerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BackroomEntities.HOUND, HoundEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(BackroomEntities.DEATHMOTH, DeathmothEntity.createHostileAttributes());
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType<T> type){
        return Registry.register(Registries.ENTITY_TYPE, ProjectBackroom.id(id), type);
    }
}
