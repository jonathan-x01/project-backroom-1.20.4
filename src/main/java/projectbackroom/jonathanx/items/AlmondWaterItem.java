package projectbackroom.jonathanx.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectbackroom.jonathanx.registry.ModStatusEffects;

public class AlmondWaterItem extends Item {
    public AlmondWaterItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.hasStatusEffect(ModStatusEffects.INSANITY)){
            user.removeStatusEffect(ModStatusEffects.INSANITY);
        }

        if (user instanceof PlayerEntity){
            PlayerEntity entity = (PlayerEntity) user;
            if (!entity.isCreative()){
                stack.decrement(1);
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
