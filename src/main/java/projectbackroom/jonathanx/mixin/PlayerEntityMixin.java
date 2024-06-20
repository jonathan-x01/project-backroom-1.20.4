package projectbackroom.jonathanx.mixin;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectbackroom.jonathanx.ProjectBackroom;

import java.awt.*;
import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract boolean isCreative();

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!this.isCreative() && source.getName().equals("inWall")){
            double chance = new Random().nextDouble();
            if (chance < 0.3){
                teleportToCustomDimension((ServerPlayerEntity) (Object) this);
            }
        }
    }

    private void teleportToCustomDimension(ServerPlayerEntity player) {
        ServerWorld serverWorld = player.getServerWorld();
        RegistryKey<World> level0Key = RegistryKey.of(RegistryKeys.WORLD, ProjectBackroom.id("level0"));
        ServerWorld level0 = serverWorld.getServer().getWorld(level0Key);
        if (level0 != null) {
            player.teleport(level0, player.getX(), 2, player.getZ(), player.getYaw(), player.getPitch());
            BlockPos blockPos = new BlockPos((int) Math.round(player.getX()), 2, (int) Math.round(player.getZ()));
            player.setSpawnPoint(level0Key, blockPos, player.getSpawnAngle(), true, false);
        }
    }
}
