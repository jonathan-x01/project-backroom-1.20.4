package projectbackroom.jonathanx.villager;

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
import projectbackroom.jonathanx.blocks.BackroomBlocks;

public class BackroomVillager {
    public static final RegistryKey<PointOfInterestType> DETECTIVE_POI_KEY = poiKey("detective_poi");
    public static final PointOfInterestType DETECTIVE_POI = registerPoi("detective_poi", BackroomBlocks.CONSPIRACY_TABLE);
    public static final VillagerProfession DETECTIVE = registerProfession("detective", DETECTIVE_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, ProjectBackroom.id(name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(ProjectBackroom.id(name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, ProjectBackroom.id(name));
    }

    public static void registerVillagers() {
        ProjectBackroom.displayRegisteredSectors(BackroomVillager.class);
    }
}
