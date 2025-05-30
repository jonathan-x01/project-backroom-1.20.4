package projectbackroom.jonathanx.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.items.ContaminatedBottleWater;
import projectbackroom.jonathanx.items.LiquidPain;
import projectbackroom.jonathanx.items.OriginalAlmondWater;

public class ModdedItems {
    public static Item ORIGINAL_ALMOND_WATER = registerFood("original_almond_water",new OriginalAlmondWater(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item LIQUID_PAIN = registerFood("liquid_pain",new LiquidPain(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item CONTAMINATED_WATER = registerFood("contaminated_bottle_water", new ContaminatedBottleWater(new Item.Settings().maxCount(1).food(new FoodComponent.Builder().hunger(1).alwaysEdible().build())));

    // Spawn Eggs
    public static Item SMILER_SPAWN_EGG = registerSpawnEgg("smiler", ModdedEntities.SMILERS, 3, 4, new Item.Settings());
    public static Item HOUND_SPAWN_EGG = registerSpawnEgg("hound", ModdedEntities.HOUND, 4, 6, new Item.Settings());
    public static Item DEATHMOTHS_SPAWN_EGG = registerSpawnEgg("deathmoth", ModdedEntities.DEATHMOTH, 6, 4, new Item.Settings());
    public static Item FACELINGS_SPAWN_EGG = registerSpawnEgg("faceling", ModdedEntities.FACELINGS, 1, 3, new Item.Settings());

    public static Item registerFood(String name, Item item){
        return register(name, item);
    }

    public static Item registerSpawnEgg(String entityName, EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Item.Settings settings){
        return register(entityName + "_spawn_egg", new SpawnEggItem(type, primaryColor, secondaryColor, settings));
    }

    public static Item register(String name, Item item){
        return Registry.register(Registries.ITEM,ProjectBackroom.id(name), item);
    }

    public static void registerModdedItems(){
        ProjectBackroom.displayRegisteredSectors(ModdedItems.class);
        ModdedItemGroups.getItemGroup("backroom_items")
                .entries(((displayContext, entries) -> {
                    entries.add(ORIGINAL_ALMOND_WATER);
                    entries.add(LIQUID_PAIN);
                    entries.add(CONTAMINATED_WATER);
                }));

        ModdedItemGroups.getItemGroup("backroom_spawn_eggs")
                .entries(((displayContext, entries) -> {
                    entries.add(SMILER_SPAWN_EGG);
                    entries.add(HOUND_SPAWN_EGG);
                    entries.add(FACELINGS_SPAWN_EGG);
                    entries.add(DEATHMOTHS_SPAWN_EGG);
                }));
    }
}
