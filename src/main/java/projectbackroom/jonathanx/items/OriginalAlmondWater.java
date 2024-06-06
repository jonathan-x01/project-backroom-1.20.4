package projectbackroom.jonathanx.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import projectbackroom.jonathanx.ProjectBackroom;

public class OriginalAlmondWater extends AlmondWaterItem {
    public OriginalAlmondWater(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack){
        return UseAction.DRINK;
    }
}
