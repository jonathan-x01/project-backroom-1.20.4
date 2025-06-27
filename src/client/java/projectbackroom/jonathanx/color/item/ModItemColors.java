package projectbackroom.jonathanx.color.item;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.GrassColors;
import projectbackroom.jonathanx.blocks.BackroomBlocks;

public class ModItemColors extends ItemColors {
    public static ItemColors create(){
        ItemColors itemColors = new ItemColors();
        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> GrassColors.getColor(0.5, 1.0)), BackroomBlocks.ALMOND_TREE_LEAVES.asItem());

        return itemColors;
    }
}
