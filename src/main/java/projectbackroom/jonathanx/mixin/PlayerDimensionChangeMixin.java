package projectbackroom.jonathanx.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.util.DebugLogger;

@Mixin(ServerPlayerEntity.class)
public class PlayerDimensionChangeMixin {

    /*@Inject(method = "moveToWorld", at = @At("RETURN"))
    private void onChangeDimension(ServerWorld destination, CallbackInfoReturnable<Entity> cir){
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        World world = player.getWorld();
        BlockPos pos = player.getBlockPos();
        //RegistryKey<DimensionOptions> dimensionKey = RegistryKey.of(RegistryKeys.DIMENSION, ProjectBackroom.id("level0"));
        DebugLogger.debug(destination.getDimensionKey().toString());
        /*if (destination.getDimensionKey()){

        }
    }*/
}
