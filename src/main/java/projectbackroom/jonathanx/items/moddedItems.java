package projectbackroom.jonathanx.items;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import projectbackroom.jonathanx.ProjectBackroom;

public class moddedItems {
    public static Item ORIGINAL_ALMOND_WATER = registerFood("original_almond_water",new originalAlmondWater(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));
    public static Item LIQUID_PAIN = registerFood("liquid_pain",new LiquidPain(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build())));

    public static final ItemGroup BACKROOM_ITEMS = FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.project_backroom.backroom_items"))
            .icon(() -> new ItemStack(ORIGINAL_ALMOND_WATER.asItem()))
            .entries(((displayContext, entries) -> {
                entries.add(ORIGINAL_ALMOND_WATER);
                entries.add(LIQUID_PAIN);
            }))
            .build();
    public static ItemGroup itemGroupRegistry = Registry.register(Registries.ITEM_GROUP, new Identifier(ProjectBackroom.MOD_ID,"backroom_items"), BACKROOM_ITEMS);
    public static Item registerFood(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(ProjectBackroom.MOD_ID,name), item);
    }

    public static void registerModdedItems(){
        ProjectBackroom.LOGGER.info("Modded items loaded | " + ProjectBackroom.MOD_ID);
    }
}
