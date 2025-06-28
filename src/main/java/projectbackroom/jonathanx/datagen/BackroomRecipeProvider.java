package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.book.RecipeCategory;
import projectbackroom.jonathanx.items.BackroomItems;

public class BackroomRecipeProvider extends FabricRecipeProvider {
    public BackroomRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, BackroomItems.ALMOND_SEED, 1)
                .input(BackroomItems.ALMOND)
                .criterion("has_almond", conditionsFromItem(BackroomItems.ALMOND))
                .offerTo(exporter);
    }
}
