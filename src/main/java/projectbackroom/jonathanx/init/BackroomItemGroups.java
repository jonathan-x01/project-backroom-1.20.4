package projectbackroom.jonathanx.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.items.BackroomItems;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.Initer;

import java.util.HashMap;
import java.util.Map;

public class BackroomItemGroups {
    public static final Map<String, ItemGroup.Builder> ITEM_GROUPS_BUILDERS = new HashMap<>();
    public static final Map<String, ItemGroup> ITEM_GROUPS = new HashMap<>();
    public static void createNewItemGroup(String id_name, ItemStack icon){
        ItemGroup.Builder ITEM_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemgroup.project_backroom." + id_name)).icon(() -> icon);
        ITEM_GROUPS_BUILDERS.put(id_name, ITEM_GROUP);
    }

    public static ItemGroup.Builder getItemGroup(String id_name){
        return ITEM_GROUPS_BUILDERS.get(id_name);
    }

    public static void buildAll(){
        ITEM_GROUPS_BUILDERS.forEach((key, value) -> {
            ItemGroup itemGroup = value.build();
            ITEM_GROUPS.put(key, Registry.register(Registries.ITEM_GROUP, ProjectBackroom.id(key),itemGroup));
        });
    }

    @Deprecated
    public static void registerModdedItemGroups(){
        DebugLogger.displayRegisteredSectors(BackroomItemGroups.class);
        createNewItemGroup("backroom_blocks", new ItemStack(BackroomBlocks.LEVEL_0_WALLPAPER.asItem()));
        createNewItemGroup("backroom_items", BackroomItems.ORIGINAL_ALMOND_WATER.getDefaultStack());
        createNewItemGroup("backroom_flora", BackroomBlocks.ALMOND_TREE_SAPLING.asItem().getDefaultStack());
        createNewItemGroup("backroom_spawn_eggs", BackroomItems.SMILER_SPAWN_EGG.getDefaultStack());
    }
}
