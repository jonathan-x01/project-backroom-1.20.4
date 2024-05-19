package projectbackroom.jonathanx.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;

public class moddedSounds {
    public static final SoundEvent SOGGY_STEPS = registerSound("soggy_steps");
    public static final SoundEvent BUZZING_LIGHTS = registerSound("buzzing_lights");

    public static SoundEvent registerSound(String name){
        Identifier ID = new Identifier(ProjectBackroom.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT, ID, SoundEvent.of(ID));
    }

    public static void registerModdedSounds(){
        ProjectBackroom.LOGGER.info("Loading modded sounds for " + ProjectBackroom.MOD_ID);
    }
}
