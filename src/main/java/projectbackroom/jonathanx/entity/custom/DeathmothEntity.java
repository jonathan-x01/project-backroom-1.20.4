package projectbackroom.jonathanx.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class DeathmothEntity extends HostileEntity {
    public DeathmothEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
}
