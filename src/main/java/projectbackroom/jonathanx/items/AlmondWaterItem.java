package projectbackroom.jonathanx.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectbackroom.jonathanx.init.BackroomStatusEffects;

public class AlmondWaterItem extends DrinkableItem {
    public AlmondWaterItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.hasStatusEffect(BackroomStatusEffects.INSANITY)){
            user.removeStatusEffect(BackroomStatusEffects.INSANITY);
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
