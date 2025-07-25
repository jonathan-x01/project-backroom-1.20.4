package projectbackroom.jonathanx.init;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

public class BackroomPotions implements Initer {
    public static final RegistryEntry<Potion> HOMEMADE_ALMOND_WATER;

    private static RegistryEntry<Potion> register(String name, StatusEffectInstance... effects) {
        RegistryKey<Potion> potionKey = RegistryKey.of(RegistryKeys.POTION, ProjectBackroom.id(name));

        return Registry.registerReference(Registries.POTION, potionKey, new Potion(name, effects));
    }

    @Override
    public void init() {
        DebugLogger.displayRegisteredSectors(BackroomPotions.class);
    }

    public static void registerPotions(){
        DebugLogger.displayRegisteredSectors(BackroomPotions.class);
    }

    static {
        HOMEMADE_ALMOND_WATER = register("homemade_almond_water", new StatusEffectInstance(StatusEffects.REGENERATION, 100));
    }
}
