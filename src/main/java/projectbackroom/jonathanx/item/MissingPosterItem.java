package projectbackroom.jonathanx.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import projectbackroom.jonathanx.init.BackroomComponents;

import java.util.List;

public class MissingPosterItem extends Item {
    public MissingPosterItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        String missingPosterName = stack.get(BackroomComponents.MISSING_POSTER_PLAYER_NAME);
        if (missingPosterName == null){
            missingPosterName = Text.translatable("missing_poster.unknown").getString();
            stack.set(BackroomComponents.MISSING_POSTER_PLAYER_NAME, missingPosterName);
        }

        tooltip.add(Text.translatable("missing_poster.ownership", missingPosterName).formatted(Formatting.WHITE));
    }
}
