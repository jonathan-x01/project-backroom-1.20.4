package projectbackroom.jonathanx.gui.screen.ingame;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.networking.s2c.ConspiracyTableS2CPayload;
import projectbackroom.jonathanx.screen.ConspiracyTableScreenHandler;
import projectbackroom.jonathanx.util.DebugLogger;

public class ConspiracyTableScreen extends HandledScreen<ConspiracyTableScreenHandler> {
    public static final Identifier TEXTURE = ProjectBackroom.id("textures/gui/conspiracy_table.png");
    private TextFieldWidget textField;

    public ConspiracyTableScreen(ConspiracyTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 174;
        this.backgroundHeight = 163;
    }

    private void sendTextToServer(){
        String text = textField.getText();
        ClientPlayNetworking.send(new ConspiracyTableS2CPayload(text));
    }

    @Override
    protected void onSlotChangedState(int slotId, int handlerId, boolean newState) {
        super.onSlotChangedState(slotId, handlerId, newState);
    }

    @Override
    protected void handledScreenTick() {
        this.textField.setEditable(isSlotsFilled());
    }

    private boolean isSlotsFilled(){
        return this.handler.getSlot(0).hasStack() && this.handler.getSlot(1).hasStack();
    }

    @Override
    protected void init() {
        super.init();

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        textField = new TextFieldWidget(textRenderer, x + 29, y + 6, 116, 16, Text.literal(""));
        textField.setMaxLength(16);
        textField.setEditable(true);
        textField.setDrawsBackground(true);

        addSelectableChild(textField);
        setInitialFocus(textField);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        textField.render(context, mouseX, mouseY, delta);

        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);

        context.drawTexture(
                RenderLayer::getGuiTextured,
                TEXTURE,
                this.x, this.y,
                1.0f, 1.0f,
                this.backgroundWidth, this.backgroundHeight,
                256,
                256,
                0xFFFFFFFF
        );

        
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (!textField.getText().isEmpty()){
            sendTextToServer();
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.textField.isActive() && this.textField.isFocused() && client != null && client.options.inventoryKey.matchesKey(keyCode, scanCode)){
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
