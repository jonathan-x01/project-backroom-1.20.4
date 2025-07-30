package projectbackroom.jonathanx.init.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import projectbackroom.jonathanx.util.Initer;

public class VillagerTrades implements Initer {
    @Override
    public void init() {
        Initer.super.init();
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
