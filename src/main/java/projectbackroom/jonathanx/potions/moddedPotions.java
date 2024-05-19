package projectbackroom.jonathanx.potions;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;

public class moddedPotions {
    //public static Potion ORIGINAL_ALMOND_WATER;

    public static Potion registerPotion(String name, Potion potion){
        return Registry.register(Registries.POTION,new Identifier(ProjectBackroom.MOD_ID,name),potion);
    }

    public static void registerModdedPotions(){
        //ORIGINAL_ALMOND_WATER = registerPotion("original_almond_water",new Potion());
        ProjectBackroom.LOGGER.info("Registered modded potions | " + ProjectBackroom.MOD_ID);
    }
}
