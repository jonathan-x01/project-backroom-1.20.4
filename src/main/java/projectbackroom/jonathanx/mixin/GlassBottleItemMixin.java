package projectbackroom.jonathanx.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectbackroom.jonathanx.block.entity.PipeBlockEntity;
import projectbackroom.jonathanx.block.pipes.PipeBlock;
import projectbackroom.jonathanx.fluid.BackroomsFlowableFluid;

@Mixin(GlassBottleItem.class)
public abstract class GlassBottleItemMixin extends Item {

    @Shadow public abstract ActionResult use(World world, PlayerEntity user, Hand hand);

    public GlassBottleItemMixin(Settings settings) {
        super(settings);
    }

    @Unique
    private void initBottleCollect(World world, PlayerEntity user, Hand hand){
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        ItemStack itemStack = user.getStackInHand(hand);
        itemStack.decrement(1);
    }

    @Inject(method = "use", at = @At("RETURN"))
    private void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        BlockHitResult result = GlassBottleItemMixin.raycast(world, user, RaycastContext.FluidHandling.NONE);
        BlockState blockState = world.getBlockState(result.getBlockPos());

        Text cannotCollect = Text.translatable("block.project_backroom.pipe.cannot_collect");

        if (blockState.getBlock() instanceof PipeBlock && blockState.get(PipeBlock.LEAKING)){
            PipeBlockEntity blockEntity = (PipeBlockEntity) world.getBlockEntity(result.getBlockPos());
            assert blockEntity != null;
            Fluid fluid = blockEntity.getFluidContainer();
            if (fluid instanceof BackroomsFlowableFluid backroomsFlowableFluid){
                if (backroomsFlowableFluid.getBottleItem() != null){
                    initBottleCollect(world, user, hand);
                    if (backroomsFlowableFluid.getBottleItem() != null){
                        user.equipStack(EquipmentSlot.MAINHAND, backroomsFlowableFluid.getBottleItem());
                    } else {
                        user.sendMessage(cannotCollect, true);
                    }
                }
            } else {
                if (fluid == Fluids.WATER){
                    initBottleCollect(world, user, hand);
                    user.equipStack(EquipmentSlot.MAINHAND, PotionContentsComponent.createStack(Items.POTION, Potions.WATER));
                } else {
                    user.sendMessage(cannotCollect, true);
                }
            }
        }
    }
}
