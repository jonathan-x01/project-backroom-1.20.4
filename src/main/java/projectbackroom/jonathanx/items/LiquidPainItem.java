package projectbackroom.jonathanx.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.world.World;

public class LiquidPainItem extends Item {
    public LiquidPainItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        user.setHealth(0f);
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack){
        return UseAction.DRINK;
    }
}
