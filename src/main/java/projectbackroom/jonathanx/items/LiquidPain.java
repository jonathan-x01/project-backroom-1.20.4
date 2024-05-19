package projectbackroom.jonathanx.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class LiquidPain extends Item {
    public LiquidPain(Settings settings) {
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
