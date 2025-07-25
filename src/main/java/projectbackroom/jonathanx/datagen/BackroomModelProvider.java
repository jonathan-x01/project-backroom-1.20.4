package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import projectbackroom.jonathanx.init.BackroomBlocks;

public class BackroomModelProvider extends FabricModelProvider {
    public BackroomModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(BackroomBlocks.ALMOND_TREE_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCross(BackroomBlocks.ALMOND_TREE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
