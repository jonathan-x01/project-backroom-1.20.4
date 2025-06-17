package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import projectbackroom.jonathanx.registry.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BROKEN_BRICKS);
        addDrop(ModBlocks.WALL_LIGHT);
        addDrop(ModBlocks.WHITE_BRICKS);

        addDrop(ModBlocks.ALMOND_TREE_SAPLING);
        addDrop(ModBlocks.ALMOND_TREE_LOG);
        addDrop(ModBlocks.ALMOND_TREE_PLANKS);

        addDrop(ModBlocks.ALMOND_TREE_LEAVES, leavesDrops(ModBlocks.ALMOND_TREE_LEAVES, ModBlocks.ALMOND_TREE_SAPLING, 0.025f));
    }
}
