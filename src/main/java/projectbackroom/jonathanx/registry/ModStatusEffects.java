package projectbackroom.jonathanx.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.entity.effect.InsanityStatusEffect;
import projectbackroom.jonathanx.entity.effect.TetanusStatusEffect;

public class ModStatusEffects {
    public static final StatusEffect INSANITY = register("insanity", new InsanityStatusEffect());
    public static final StatusEffect TETANUS = register("tetanus", new TetanusStatusEffect());

    public static StatusEffect register(String name, StatusEffect effect){
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(ProjectBackroom.MOD_ID,name),effect);
    }

    public static void registerModdedStatusEffects(){
        ProjectBackroom.displayRegisteredSectors(ModStatusEffects.class);
    }
}
