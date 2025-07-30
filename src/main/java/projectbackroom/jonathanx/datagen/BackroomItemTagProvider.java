package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import projectbackroom.jonathanx.init.BackroomItems;

import java.util.concurrent.CompletableFuture;

public class BackroomItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public BackroomItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(BackroomItems.RUSTY_TIPPED_ARROW);
    }
}
