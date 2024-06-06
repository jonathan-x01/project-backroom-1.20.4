package projectbackroom.jonathanx.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;

public class ModdedStatusEffects {
    public static final StatusEffect INSANITY = register("insanity", new InsanityStatusEffect());

    public static StatusEffect register(String name, StatusEffect effect){
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(ProjectBackroom.MOD_ID,name),effect);
    }

    public static void registerModdedStatusEffects(){
        ProjectBackroom.LOGGER.info("Registered Status Effects");
    }
}
