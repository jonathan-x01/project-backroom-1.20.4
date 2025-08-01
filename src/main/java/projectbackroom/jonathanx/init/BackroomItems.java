package projectbackroom.jonathanx.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.apache.http.annotation.Obsolete;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.items.*;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

import java.util.function.Function;

public class BackroomItems implements Initer {
    // Misc items
    public static Item ORIGINAL_ALMOND_WATER = register("original_almond_water", OriginalAlmondWaterItem::new, new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build()));
    public static Item ALMOND_MILK_BUCKET = register("almond_milk_bucket", AlmondMilkBucketItem::new, new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build()).recipeRemainder(Items.BUCKET));
    public static Item LIQUID_PAIN = register("liquid_pain", LiquidPainItem::new, new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build()));
    public static Item CONTAMINATED_WATER = register("contaminated_bottle_water", ContaminatedBottleWaterItem::new, new Item.Settings().maxCount(1).food(new FoodComponent.Builder().nutrition(1).alwaysEdible().build()));
    public static Item ALMOND_SEED = register("almond_seed", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(2).build()));
    public static Item ALMOND_HUSK = register("almond_husk", Item::new, new Item.Settings());
    public static Item ALMOND = register("almond", Item::new, new Item.Settings().recipeRemainder(ALMOND_HUSK));
    public static Item RUSTY_SHARD = register("rusty_shard", Item::new, new Item.Settings());
    public static Item RUSTY_TIPPED_ARROW = register("rusty_tipped_arrow", RustyTippedArrowItem::new, new Item.Settings());
    public static Item MISSING_POSTER = register("missing_poster", MissingPosterItem::new, new Item.Settings());

    // Spawn Eggs
    public static Item SMILER_SPAWN_EGG = registerSpawnEgg("smiler", BackroomEntities.SMILER, 0, 16777215);
    public static Item HOUND_SPAWN_EGG = registerSpawnEgg("hound", BackroomEntities.HOUND, 56063, 44543);
    public static Item DEATHMOTHS_SPAWN_EGG = registerSpawnEgg("deathmoth", BackroomEntities.DEATHMOTH, 56063, 44543);
    public static Item FACELINGS_SPAWN_EGG = registerSpawnEgg("faceling", BackroomEntities.FACELING, 962222, 11107684);

    @Deprecated
    public static Item registerFood(String name, Item item){
        return register(name, item);
    }

    public static Item registerSpawnEgg(String entityName, EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor){
        return register(entityName + "_spawn_egg", settings -> new SpawnEggItem(type, primaryColor, secondaryColor, settings));
    }

    public static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings itemSettings){
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, ProjectBackroom.id(id));
        Item item = factory.apply(itemSettings.registryKey(itemKey));
        return Registry.register(Registries.ITEM, itemKey, item);
    }
    
    public static Item register(String id, Function<Item.Settings, Item> factory){
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, ProjectBackroom.id(id));
        Item item = factory.apply(new Item.Settings().registryKey(itemKey));
        return Registry.register(Registries.ITEM, itemKey, item);
    }

    public static Item register(String name, Item item){
        return Registry.register(Registries.ITEM,ProjectBackroom.id(name), item);
    }

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init() {
        Initer.super.init();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(Items.ARROW, RUSTY_TIPPED_ARROW);
        });

        BackroomItemGroups.getItemGroup("backroom_items")
                .entries(((displayContext, entries) -> {
                    entries.add(ORIGINAL_ALMOND_WATER);
                    entries.add(LIQUID_PAIN);
                    entries.add(CONTAMINATED_WATER);
                    entries.add(ALMOND_MILK_BUCKET);
                    entries.add(ALMOND);
                    entries.add(ALMOND_HUSK);
                    entries.add(ALMOND_SEED);
                    entries.add(RUSTY_SHARD);
                    entries.add(MISSING_POSTER);
                }));

        BackroomItemGroups.getItemGroup("backroom_spawn_eggs")
                .entries(((displayContext, entries) -> {
                    entries.add(SMILER_SPAWN_EGG);
                    entries.add(HOUND_SPAWN_EGG);
                    entries.add(FACELINGS_SPAWN_EGG);
                    entries.add(DEATHMOTHS_SPAWN_EGG);
                }));
    }
}
