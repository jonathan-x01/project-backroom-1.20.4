package projectbackroom.jonathanx.block;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.init.BackroomSounds;

public class BackroomBlockSoundGroups {
    public static final BlockSoundGroup SOGGY_CARPET = new BlockSoundGroup(
            1f,
            1f,
            SoundEvent.of(Identifier.tryParse("minecraft:block.wool.break")),
            BackroomSounds.SOGGY_STEPS,
            SoundEvent.of(Identifier.tryParse("minecraft:block.wool.place")),
            SoundEvent.of(Identifier.tryParse("minecraft:block.wool.hit")),
            SoundEvent.of(Identifier.tryParse("minecraft:block.wool.fall"))
    );
}
