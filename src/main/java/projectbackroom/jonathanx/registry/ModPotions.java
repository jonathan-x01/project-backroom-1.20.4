package projectbackroom.jonathanx.registry;

import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;

public class ModPotions {
    //public static Potion ORIGINAL_ALMOND_WATER;

    public static Potion registerPotion(String name, Potion potion){
        return Registry.register(Registries.POTION,new Identifier(ProjectBackroom.MOD_ID,name),potion);
    }

    public static void registerModdedPotions(){
        //ORIGINAL_ALMOND_WATER = registerPotion("original_almond_water",new Potion());
        ProjectBackroom.displayRegisteredSectors(ModPotions.class);
    }
}
