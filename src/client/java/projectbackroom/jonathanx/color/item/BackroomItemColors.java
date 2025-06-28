package projectbackroom.jonathanx.color.item;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import projectbackroom.jonathanx.blocks.BackroomBlocks;
import projectbackroom.jonathanx.registry.BackroomPotions;

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

                    Potion potion = PotionUtil.getPotion(stack);
                    if (potion == BackroomPotions.HOMEMADE_ALMOND_WATER) {
                        return 0xD2B48C;
                    }

                    // fallback to default color logic
                    return PotionUtil.getColor(stack);
                }), Items.POTION
        );

        return itemColors;
    }
}
