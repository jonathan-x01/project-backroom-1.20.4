package projectbackroom.jonathanx.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectbackroom.jonathanx.init.BackroomItems;
import projectbackroom.jonathanx.init.BackroomEntities;
import projectbackroom.jonathanx.init.BackroomStatusEffects;

import java.util.concurrent.ThreadLocalRandom;

public class RustyTippedArrowEntity extends PersistentProjectileEntity {
    private static final ItemStack DEFAULT_STACK;

    public RustyTippedArrowEntity(EntityType<? extends RustyTippedArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public RustyTippedArrowEntity(World world, LivingEntity owner, ItemStack stack, ItemStack shotFram){
        super(BackroomEntities.RUSTY_TIPPED_ARROW, owner, world, stack, shotFram);
    }

    public RustyTippedArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(BackroomEntities.RUSTY_TIPPED_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        int percentage = ThreadLocalRandom.current().nextInt(0, 101);
        if (percentage < 50){
            target.addStatusEffect(new StatusEffectInstance(BackroomStatusEffects.TETANUS, 3600, 1));
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return null;
    }

    static {
        DEFAULT_STACK = new ItemStack(BackroomItems.RUSTY_TIPPED_ARROW);
    }
}
