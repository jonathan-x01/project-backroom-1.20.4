package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.items.BackroomItems;

import java.util.List;

public class BackroomRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> RUSTY_SMELTABLES = List.of(BackroomItems.RUSTY_SHARD);
    public BackroomRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, RUSTY_SMELTABLES, RecipeCategory.MISC, Items.IRON_NUGGET, 0.5f, 150, "rusty_shard");
        offerBlasting(exporter, RUSTY_SMELTABLES, RecipeCategory.MISC, Items.IRON_NUGGET, 0.5f, 75, "rusty_shard");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, BackroomItems.ALMOND_SEED, 1)
                .input(BackroomItems.ALMOND)
                .criterion("has_almond", conditionsFromItem(BackroomItems.ALMOND))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, BackroomItems.RUSTY_TIPPED_ARROW, 3)
                .pattern(" R ")
                .pattern(" S ")
                .pattern(" F ")
                .input('R',BackroomItems.RUSTY_SHARD)
                .input('S', Items.STICK)
                .input('F', Items.FEATHER)
                .criterion(hasItem(BackroomItems.RUSTY_SHARD), conditionsFromItem(BackroomItems.RUSTY_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(BackroomItems.RUSTY_TIPPED_ARROW)));
    }
}
