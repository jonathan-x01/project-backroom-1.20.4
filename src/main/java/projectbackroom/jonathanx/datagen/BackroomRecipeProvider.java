package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.items.BackroomItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BackroomRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> RUSTY_SMELTABLES = List.of(BackroomItems.RUSTY_SHARD);
    public BackroomRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                offerSmelting(RUSTY_SMELTABLES, RecipeCategory.MISC, Items.IRON_NUGGET, 0.5f, 150, "rusty_shard");
                offerBlasting(RUSTY_SMELTABLES, RecipeCategory.MISC, Items.IRON_NUGGET, 0.5f, 75, "rusty_shard");

                createShapeless(RecipeCategory.FOOD, BackroomItems.ALMOND_SEED, 1)
                        .input(BackroomItems.ALMOND)
                        .criterion("has_almond", conditionsFromItem(BackroomItems.ALMOND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, BackroomItems.RUSTY_TIPPED_ARROW, 3)
                        .pattern(" R ")
                        .pattern(" S ")
                        .pattern(" F ")
                        .input('R',BackroomItems.RUSTY_SHARD)
                        .input('S', Items.STICK)
                        .input('F', Items.FEATHER)
                        .criterion(hasItem(BackroomItems.RUSTY_SHARD), conditionsFromItem(BackroomItems.RUSTY_SHARD))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(BackroomItems.RUSTY_TIPPED_ARROW))));

                createShaped(RecipeCategory.FOOD, BackroomItems.ALMOND_MILK_BUCKET, 1)
                        .pattern("AAA")
                        .pattern("AMA")
                        .pattern("AAA")
                        .input('A', BackroomItems.ALMOND_SEED)
                        .input('M', Items.MILK_BUCKET)
                        .criterion(hasItem(BackroomItems.ALMOND_SEED), conditionsFromItem(BackroomItems.ALMOND_SEED))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(getRecipeName(BackroomItems.ALMOND_MILK_BUCKET))));
            }
        };
    }

    @Override
    public String getName() {
        return "Project Backroom Recipes";
    }
}
