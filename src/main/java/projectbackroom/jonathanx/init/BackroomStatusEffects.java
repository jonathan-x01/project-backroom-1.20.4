package projectbackroom.jonathanx.init;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.effect.InsanityStatusEffect;
import projectbackroom.jonathanx.entity.effect.TetanusStatusEffect;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

public class BackroomStatusEffects implements Initer {
    public static final RegistryEntry<StatusEffect> INSANITY = register("insanity", new InsanityStatusEffect());
    public static final RegistryEntry<StatusEffect> TETANUS = register("tetanus", new TetanusStatusEffect());

    public static RegistryEntry<StatusEffect> register(String name, StatusEffect effect){
        return Registry.registerReference(Registries.STATUS_EFFECT, ProjectBackroom.id(name), effect);
    }

    @Deprecated
    public static void registerModdedStatusEffects(){
        DebugLogger.displayRegisteredSectors(BackroomStatusEffects.class);
    }
}
