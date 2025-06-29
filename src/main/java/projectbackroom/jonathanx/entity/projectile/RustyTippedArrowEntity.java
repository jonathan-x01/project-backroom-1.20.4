package projectbackroom.jonathanx.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.items.BackroomItems;
import projectbackroom.jonathanx.registry.BackroomEntities;
import projectbackroom.jonathanx.registry.ModStatusEffects;

import java.util.concurrent.ThreadLocalRandom;

public class RustyTippedArrowEntity extends PersistentProjectileEntity {
    private static final ItemStack DEFAULT_STACK;

    public RustyTippedArrowEntity(EntityType<RustyTippedArrowEntity> type, World world) {
        super(type, world, DEFAULT_STACK);
    }

    public RustyTippedArrowEntity(World world, LivingEntity owner, ItemStack stack){
        super(BackroomEntities.RUSTY_TIPPED_ARROW, owner, world, stack);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        int percentage = ThreadLocalRandom.current().nextInt(0, 101);
        if (percentage < 50){
            target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.TETANUS, 3600, 1));
        }
    }

    static {
        DEFAULT_STACK = new ItemStack(BackroomItems.RUSTY_TIPPED_ARROW);
    }
}
