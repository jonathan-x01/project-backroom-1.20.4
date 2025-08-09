package projectbackroom.jonathanx.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AlmondMilkBucketItem extends DrinkableItem {
    public AlmondMilkBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (!world.isClient) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH));
        }

        return super.finishUsing(stack, world, user);
    }
}
