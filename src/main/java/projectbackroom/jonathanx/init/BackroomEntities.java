package projectbackroom.jonathanx.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.hostile.DeathmothEntity;
import projectbackroom.jonathanx.entity.neutral.FacelingEntity;
import projectbackroom.jonathanx.entity.hostile.HoundEntity;
import projectbackroom.jonathanx.entity.hostile.SmilerEntity;
import projectbackroom.jonathanx.entity.projectile.RustyTippedArrowEntity;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

public class BackroomEntities implements Initer {
    public static final RegistryKey<EntityType<?>> FACELING_KEY = keyOf("faceling");
    public static final RegistryKey<EntityType<?>> SMILER_KEY = keyOf("smiler");
    public static final RegistryKey<EntityType<?>> HOUND_KEY = keyOf("hound");
    public static final RegistryKey<EntityType<?>> DEATHMOTH_KEY = keyOf("deathmoth");

    public static final RegistryKey<EntityType<?>> RUSTY_TIPPED_ARROW_KEY = keyOf("rusty_tipped_arrow");

    /*public static EntityType<FacelingEntity> FACELING_1 = register(
            FACELING_KEY.getRegistry().getPath(),
            EntityType.Builder.create(FacelingEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
    );*/


    public static EntityType<FacelingEntity> FACELING = Registry.register(
            Registries.ENTITY_TYPE,
            FACELING_KEY,
            EntityType.Builder.create(FacelingEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ProjectBackroom.id(FACELING_KEY.getRegistry().getPath())))
    );

    /*public static EntityType<SmilerEntity> SMILER_1 = register(
            SMILER_KEY.getRegistry().getPath(),
            EntityType.Builder.create(SmilerEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
    );*/


    public static EntityType<SmilerEntity> SMILER = Registry.register(
            Registries.ENTITY_TYPE,
            SMILER_KEY,
            EntityType.Builder.create(SmilerEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ProjectBackroom.id(SMILER_KEY.getRegistry().getPath())))
    );

    /*public static EntityType<HoundEntity> HOUND_1 = register(
            HOUND_KEY.getRegistry().getPath(),
            EntityType.Builder.create(HoundEntity::new, SpawnGroup.MONSTER)
                    .dimensions(2f, 1f)
    );*/


    public static EntityType<HoundEntity> HOUND = Registry.register(
            Registries.ENTITY_TYPE,
            HOUND_KEY,
            EntityType.Builder.create(HoundEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ProjectBackroom.id(HOUND_KEY.getRegistry().getPath())))
    );

    /*public static EntityType<DeathmothEntity> DEATHMOTH_1 = register(
            DEATHMOTH_KEY.getRegistry().getPath(),
            EntityType.Builder.create(DeathmothEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
    );*/


    public static EntityType<DeathmothEntity> DEATHMOTH = Registry.register(
            Registries.ENTITY_TYPE,
            DEATHMOTH_KEY,
            EntityType.Builder.create(DeathmothEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 2f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ProjectBackroom.id(DEATHMOTH_KEY.getRegistry().getPath())))
    );

    // projectile
    /*public static EntityType<RustyTippedArrowEntity> RUSTY_TIPPED_ARROW_1 = register(
            RUSTY_TIPPED_ARROW_KEY.getRegistry().getPath(),
            EntityType.Builder.<RustyTippedArrowEntity>create(RustyTippedArrowEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );*/


    public static EntityType<RustyTippedArrowEntity> RUSTY_TIPPED_ARROW = Registry.register(
            Registries.ENTITY_TYPE,
            RUSTY_TIPPED_ARROW_KEY,
            EntityType.Builder.<RustyTippedArrowEntity>create(RustyTippedArrowEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, ProjectBackroom.id(RUSTY_TIPPED_ARROW_KEY.getRegistry().getPath())))
    );

    @Override
    public void init() {
        Initer.super.init();
        FabricDefaultAttributeRegistry.register(BackroomEntities.FACELING, FacelingEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(BackroomEntities.SMILER, SmilerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BackroomEntities.HOUND, HoundEntity.createAttribute());
        FabricDefaultAttributeRegistry.register(BackroomEntities.DEATHMOTH, DeathmothEntity.createHostileAttributes());
    }

    private static RegistryKey<EntityType<?>> keyOf(String name){
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, ProjectBackroom.id(name));
    }

    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type){
        return register(keyOf(id), type);
    }
}
