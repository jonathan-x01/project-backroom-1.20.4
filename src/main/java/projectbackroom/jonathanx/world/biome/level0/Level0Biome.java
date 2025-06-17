package projectbackroom.jonathanx.world.biome.level0;

import net.minecraft.client.sound.MusicType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.SpawnSettings;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.registry.ModSounds;

public class Level0Biome {
    public static final RegistryKey<Biome> LEVEL_0 = RegistryKey.of(RegistryKeys.BIOME, ProjectBackroom.id("level_0"));

    public static void boostrap(Registerable<Biome> context) {
        ProjectBackroom.displayRegisteredSectors(Level0Biome.class);
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
                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.LEVEL0_AMBIENCE)))
                        .fogColor(0)
                        .skyColor(0)
                        .waterColor(8211661)
                        .waterFogColor(329011)
                        .build())
                .build();
    }
}
