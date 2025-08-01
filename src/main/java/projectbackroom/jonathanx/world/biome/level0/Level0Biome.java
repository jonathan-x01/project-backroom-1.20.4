package projectbackroom.jonathanx.world.biome.level0;

import net.fabricmc.api.EnvType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.SpawnSettings;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.init.BackroomSounds;
import projectbackroom.jonathanx.util.DebugLogger;

public class Level0Biome {
    public static final RegistryKey<Biome> LEVEL_0 = RegistryKey.of(RegistryKeys.BIOME, ProjectBackroom.id("level_0"));

    public static void boostrap(Registerable<Biome> context) {
        DebugLogger.displayRegisteredSectors(EnvType.SERVER ,Level0Biome.class);
        //context.register(LEVEL_0, setBiome(context));
    }

    private static Biome setBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder()
                .creatureSpawnProbability(0);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .music(new MusicSound(RegistryEntry.of(BackroomSounds.LEVEL0_AMBIENCE), 0, 5, true))
                        .fogColor(0)
                        .skyColor(0)
                        .waterColor(8211661)
                        .waterFogColor(329011)
                        .build())
                .build();
    }
}
