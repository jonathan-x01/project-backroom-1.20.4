package projectbackroom.jonathanx.init.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

public class BackroomVillager implements Initer {
    public static final RegistryKey<PointOfInterestType> DETECTIVE_POI_KEY =
            RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, ProjectBackroom.id("detective_poi"));

    public static final PointOfInterestType DETECTIVE_POI =
            PointOfInterestHelper.register(ProjectBackroom.id("detective_poi"), 1, 1, BackroomBlocks.CONSPIRACY_TABLE);

    public static final VillagerProfession DETECTIVE = Registry.register(
            Registries.VILLAGER_PROFESSION,
            ProjectBackroom.id("detective"),
            new VillagerProfession(
                    "detective",
                    entry -> entry.matchesKey(DETECTIVE_POI_KEY),
                    entry -> entry.matchesKey(DETECTIVE_POI_KEY),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.ENTITY_VILLAGER_WORK_CARTOGRAPHER
            )
    );
}
