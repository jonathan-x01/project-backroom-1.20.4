package projectbackroom.jonathanx.entity.hostile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;

public class DeathmothEntity extends HostileEntity {
    private static final TrackedData<Integer> GENDER = DataTracker.registerData(DeathmothEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public DeathmothEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.dataTracker.set(GENDER, Math.round((float) Math.random()));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        this.dataTracker.set(GENDER, -1);
    }
}
