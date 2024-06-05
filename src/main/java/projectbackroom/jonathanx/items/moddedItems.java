package projectbackroom.jonathanx.items;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.itemGroups.ModdedItemGroups;

public class ModdedItems {
    public static Item ORIGINAL_ALMOND_WATER = registerFood("original_almond_water",new originalAlmondWater(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item LIQUID_PAIN = registerFood("liquid_pain",new LiquidPain(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item CONTAMINATED_WATER = registerFood("contaminated_bottle_water", new ContaminatedBottleWater(new Item.Settings().maxCount(1).food(new FoodComponent.Builder().hunger(1).alwaysEdible().build())));

    public static Item registerFood(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(ProjectBackroom.MOD_ID,name), item);
    }

    public static void registerModdedItems(){
        ProjectBackroom.LOGGER.info("Modded items loaded | " + ProjectBackroom.MOD_ID);
        ModdedItemGroups.getItemGroup("backroom_items")
                .entries(((displayContext, entries) -> {
                    entries.add(ORIGINAL_ALMOND_WATER);
                    entries.add(LIQUID_PAIN);
                    entries.add(CONTAMINATED_WATER);
                }));
    }
}
