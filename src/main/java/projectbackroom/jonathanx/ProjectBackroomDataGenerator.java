package projectbackroom.jonathanx;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import projectbackroom.jonathanx.datagen.*;
import projectbackroom.jonathanx.world.gen.features.ModPlacedFeature;
import projectbackroom.jonathanx.world.gen.features.ModConfigureFeatures;

public class ProjectBackroomDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(BackroomBlockTagProvider::new);
		pack.addProvider(BackroomItemTagProvider::new);
		pack.addProvider(BackroomLootTableProvider::new);
		pack.addProvider(BackroomModelProvider::new);
		pack.addProvider(BackroomWorldGenerator::new);
		pack.addProvider(BackroomRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		//registryBuilder.addRegistry(RegistryKeys.BIOME, Level0Biome::boostrap);

		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfigureFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeature::bootstrap);
	}
}
