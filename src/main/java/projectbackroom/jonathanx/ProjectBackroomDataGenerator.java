package projectbackroom.jonathanx;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import projectbackroom.jonathanx.datagen.ModLootTableProvider;
import projectbackroom.jonathanx.datagen.ModModelProvider;
import projectbackroom.jonathanx.datagen.ModWorldGenerator;
import projectbackroom.jonathanx.world.biome.level0.Level0Biome;
import projectbackroom.jonathanx.world.gen.features.ModPlacedFeature;
import projectbackroom.jonathanx.world.gen.features.ModConfigureFeatures;

public class ProjectBackroomDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.BIOME, Level0Biome::boostrap);

		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfigureFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeature::bootstrap);
	}
}
