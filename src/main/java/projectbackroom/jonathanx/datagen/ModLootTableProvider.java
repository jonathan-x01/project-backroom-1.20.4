package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import projectbackroom.jonathanx.blocks.BackroomBlocks;
import projectbackroom.jonathanx.items.BackroomItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(BackroomBlocks.BROKEN_BRICKS);
        addDrop(BackroomBlocks.WALL_LIGHT);
        addDrop(BackroomBlocks.WHITE_BRICKS);

        addDrop(BackroomBlocks.ALMOND_TREE_SAPLING);
        addDrop(BackroomBlocks.ALMOND_TREE_LOG);
        addDrop(BackroomBlocks.ALMOND_TREE_PLANKS);

        addDrop(BackroomBlocks.ALMOND_TREE_LEAVES,
                leavesDrops(
                        BackroomBlocks.ALMOND_TREE_LEAVES,
                        BackroomBlocks.ALMOND_TREE_SAPLING,
                        0.025f)
                .pool(LootPool.builder()
                        .rolls(
                                ConstantLootNumberProvider.create(1.0f)
                        ).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with(
                                addSurvivesExplosionCondition(
                                    BackroomBlocks.ALMOND_TREE_LEAVES,
                                    ItemEntry.builder(BackroomItems.ALMOND)
                                )
                        ).conditionally(
                                TableBonusLootCondition.builder(
                                        Enchantments.FORTUNE,
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
