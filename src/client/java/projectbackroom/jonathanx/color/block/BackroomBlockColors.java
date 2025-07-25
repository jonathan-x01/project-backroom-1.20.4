package projectbackroom.jonathanx.color.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.world.biome.FoliageColors;
import projectbackroom.jonathanx.init.BackroomBlocks;

@Environment(EnvType.CLIENT)
public class BackroomBlockColors extends BlockColors {
    public static BackroomBlockColors create(){
        BackroomBlockColors modBlockColors = new BackroomBlockColors();
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                return BiomeColors.getFoliageColor(world, pos);
            }
            return FoliageColors.getSpruceColor();
        }, BackroomBlocks.ALMOND_TREE_LEAVES);

        return modBlockColors;
    }
}
