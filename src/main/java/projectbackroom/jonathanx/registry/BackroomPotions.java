package projectbackroom.jonathanx.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import projectbackroom.jonathanx.ProjectBackroom;

public class BackroomPotions {
    public static final Potion HOMEMADE_ALMOND_WATER;
    private static Potion register(String name, Potion potion) {
        return (Potion) Registry.register(Registries.POTION, ProjectBackroom.id(name), potion);
    }

    static {
        HOMEMADE_ALMOND_WATER = register("homemade_almond_water", new Potion());
    }
}
