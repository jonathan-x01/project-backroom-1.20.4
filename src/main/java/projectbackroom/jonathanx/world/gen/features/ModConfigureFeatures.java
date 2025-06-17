package projectbackroom.jonathanx.world.gen.features;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.registry.ModBlocks;

public class ModConfigureFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ALMOND_TREE_KEY = registerKey("almond_tree");
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){
        ProjectBackroom.displayRegisteredSectors(ModConfigureFeatures.class);
        context.register(ALMOND_TREE_KEY, new ConfiguredFeature<>(
                Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.ALMOND_TREE_LOG),
                    new StraightTrunkPlacer(5,4,3),
                    BlockStateProvider.of(ModBlocks.ALMOND_TREE_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(3), 2),
                    new TwoLayersFeatureSize(1,0,2)
                ).build()
        ));
        /*register(context, ALMOND_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(ModBlocks.ALMOND_TREE_LOG),
            new StraightTrunkPlacer(5,4,3),
            BlockStateProvider.of(ModBlocks.LEVEL_0_DROPCEILING),
            new BlobFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(3), 2),
            new TwoLayersFeatureSize(1,0,2)
        ).build());*/
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ProjectBackroom.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
