package projectbackroom.jonathanx.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.registry.ModEntities;
import projectbackroom.jonathanx.registry.ModItemGroups;

public class BackroomItems {
    public static Item ORIGINAL_ALMOND_WATER = registerFood("original_almond_water",new OriginalAlmondWater(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item LIQUID_PAIN = registerFood("liquid_pain",new LiquidPain(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item CONTAMINATED_WATER = registerFood("contaminated_bottle_water", new ContaminatedBottleWater(new Item.Settings().maxCount(1).food(new FoodComponent.Builder().hunger(1).alwaysEdible().build())));
    public static Item ALMOND_SEED = registerFood("almond_seed", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).build())));
    public static Item ALMOND_HUSK = register("almond_husk", new Item(new FabricItemSettings()));
    public static Item ALMOND = register("almond", new Item(new FabricItemSettings().recipeRemainder(ALMOND_HUSK)));

    // Spawn Eggs
    public static Item SMILER_SPAWN_EGG = registerSpawnEgg("smiler", ModEntities.SMILERS, 0, 16777215, new Item.Settings());
    public static Item HOUND_SPAWN_EGG = registerSpawnEgg("hound", ModEntities.HOUND, 56063, 44543, new Item.Settings());
    public static Item DEATHMOTHS_SPAWN_EGG = registerSpawnEgg("deathmoth", ModEntities.DEATHMOTH, 56063, 44543, new Item.Settings());
    public static Item FACELINGS_SPAWN_EGG = registerSpawnEgg("faceling", ModEntities.FACELINGS, 962222, 11107684, new Item.Settings());

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
        ProjectBackroom.displayRegisteredSectors(BackroomItems.class);

        ModItemGroups.getItemGroup("backroom_items")
                .entries(((displayContext, entries) -> {
                    entries.add(ORIGINAL_ALMOND_WATER);
                    entries.add(LIQUID_PAIN);
                    entries.add(CONTAMINATED_WATER);
                    entries.add(ALMOND);
                    entries.add(ALMOND_HUSK);
                    entries.add(ALMOND_SEED);
                }));

        ModItemGroups.getItemGroup("backroom_spawn_eggs")
                .entries(((displayContext, entries) -> {
                    entries.add(SMILER_SPAWN_EGG);
                    entries.add(HOUND_SPAWN_EGG);
                    entries.add(FACELINGS_SPAWN_EGG);
                    entries.add(DEATHMOTHS_SPAWN_EGG);
                }));
    }
}
