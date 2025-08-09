package projectbackroom.jonathanx.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextIconButtonWidget;
import net.minecraft.text.Text;
import projectbackroom.jonathanx.ProjectBackroom;

@Environment(EnvType.CLIENT)
public class DiscordButtons {
    public static TextIconButtonWidget createDiscordButton(int width, ButtonWidget.PressAction onPress, boolean hideText){
        return TextIconButtonWidget.builder(Text.translatable("button.link.discord"), onPress, hideText)
                .width(width)
                .texture(ProjectBackroom.id("icon/discord"), 15, 15)
                .build();
    }


}
