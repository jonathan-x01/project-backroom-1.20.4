package projectbackroom.jonathanx.mixin.client;

import net.minecraft.client.render.Camera;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import projectbackroom.jonathanx.util.CameraAccessor;

@Mixin(Camera.class)
public abstract class CameraMixin implements CameraAccessor {
    @Shadow private BlockView area;

    @Shadow @Final private BlockPos.Mutable blockPos;

    @Unique
    @Override
    public BlockView getArea(){
        return this.area;
    }

    @Unique
    @Override
    public BlockPos getBlockPos(){
        return this.blockPos;
    }
}
