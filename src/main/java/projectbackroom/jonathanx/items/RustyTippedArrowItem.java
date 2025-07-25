package projectbackroom.jonathanx.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.entity.projectile.RustyTippedArrowEntity;

public class RustyTippedArrowItem extends ArrowItem {
    public RustyTippedArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new RustyTippedArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }
}
