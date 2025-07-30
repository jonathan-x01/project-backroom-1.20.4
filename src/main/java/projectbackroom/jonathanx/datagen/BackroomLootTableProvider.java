package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.init.BackroomItems;

import java.util.concurrent.CompletableFuture;

public class BackroomLootTableProvider extends FabricBlockLootTableProvider {

    public BackroomLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(BackroomBlocks.BROKEN_BRICKS);
        addDrop(BackroomBlocks.WALL_LIGHT);
        addDrop(BackroomBlocks.WHITE_BRICKS);

        addDrop(BackroomBlocks.ALMOND_TREE_SAPLING);
        addDrop(BackroomBlocks.ALMOND_TREE_LOG);
        addDrop(BackroomBlocks.ALMOND_TREE_PLANKS);

        addDrop(BackroomBlocks.LARGE_HORIZONTAL_PIPE, Items.IRON_NUGGET);
        addDrop(BackroomBlocks.VERTICAL_PIPE, Items.IRON_NUGGET);
        addDrop(BackroomBlocks.CEILING_PIPE, Items.IRON_NUGGET);

        addDrop(BackroomBlocks.ALMOND_TREE_LEAVES,
                leavesDrops(
                        BackroomBlocks.ALMOND_TREE_LEAVES,
                        BackroomBlocks.ALMOND_TREE_SAPLING,
                        0.025f)
                .pool(LootPool.builder()
                        .rolls(
                                ConstantLootNumberProvider.create(1.0f)
                        ).conditionally(createWithoutShearsOrSilkTouchCondition())
                        .with(
                                addSurvivesExplosionCondition(
                                    BackroomBlocks.ALMOND_TREE_LEAVES,
                                    ItemEntry.builder(BackroomItems.ALMOND)
                                )
                        ).conditionally(
                                TableBonusLootCondition.builder(
                                        impl.getOrThrow(Enchantments.FORTUNE),
                                        0.005F,
                                        0.0055555557F,
                                        0.00625F,
                                        0.008333334F,
                                        0.025F
                                )
                        )
                ));
    }
}
