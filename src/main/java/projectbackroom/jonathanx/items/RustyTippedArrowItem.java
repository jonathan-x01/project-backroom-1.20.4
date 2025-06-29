package projectbackroom.jonathanx.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectbackroom.jonathanx.entity.projectile.RustyTippedArrowEntity;

public class RustyTippedArrowItem extends ArrowItem {
    public RustyTippedArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new RustyTippedArrowEntity(world, shooter, stack.copyWithCount(1));
    }
}
