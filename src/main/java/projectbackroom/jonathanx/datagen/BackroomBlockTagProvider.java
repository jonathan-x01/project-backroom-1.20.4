package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import projectbackroom.jonathanx.blocks.BackroomBlocks;

import java.util.concurrent.CompletableFuture;

public class BackroomBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BackroomBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(BackroomBlocks.ALMOND_TREE_LOG)
                .add(BackroomBlocks.ALMOND_TREE_PLANKS);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(BackroomBlocks.ALMOND_TREE_LOG);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(BackroomBlocks.ALMOND_TREE_LEAVES);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(BackroomBlocks.ALMOND_TREE_FENCE);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(BackroomBlocks.ALMOND_TREE_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(BackroomBlocks.ALMOND_TREE_FENCE_GATE);
    }
}
