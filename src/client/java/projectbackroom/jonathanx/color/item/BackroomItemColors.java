package projectbackroom.jonathanx.color.item;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.GrassColors;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.init.BackroomPotions;

public class BackroomItemColors extends ItemColors {
    public static ItemColors create(){
        ItemColors itemColors = new ItemColors();
        ColorProviderRegistry.ITEM.register(
                ((stack, tintIndex) ->
                        GrassColors.getColor(0.5, 1.0)
                ), BackroomBlocks.ALMOND_TREE_LEAVES.asItem()
        );

        ColorProviderRegistry.ITEM.register(
                ((stack, tintIndex) -> {
                    if (tintIndex != 0) return -1;


                    Item item = PotionContentsComponent.createStack(stack.getItem(), BackroomPotions.HOMEMADE_ALMOND_WATER).getItem();
                    if (item == BackroomPotions.HOMEMADE_ALMOND_WATER) {
                        return 0xD2B48C;
                    }

                    // fallback to default color logic
                    return PotionContentsComponent.getColor(BackroomPotions.HOMEMADE_ALMOND_WATER);
                }), Items.POTION
        );

        return itemColors;
    }
}
