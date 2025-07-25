package projectbackroom.jonathanx.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class VillagerTrades {
    public static void registerVillagerTrades(){
        TradeOfferHelper.registerVillagerOffers(BackroomVillager.DETECTIVE, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 2),
                            new ItemStack(Items.SPYGLASS, 1),
                            2, 7, 0.075F
                    ));
                }
        );
    }
}
