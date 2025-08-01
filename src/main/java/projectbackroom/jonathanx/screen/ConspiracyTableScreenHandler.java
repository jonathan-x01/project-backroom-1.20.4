package projectbackroom.jonathanx.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import projectbackroom.jonathanx.init.BackroomComponents;
import projectbackroom.jonathanx.init.BackroomItems;
import projectbackroom.jonathanx.init.BackroomScreenHandlerType;
import projectbackroom.jonathanx.util.DebugLogger;
import projectbackroom.jonathanx.util.MojangUUIDFetcher;

public class ConspiracyTableScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private String clientText = "";

    private final Inventory input = new SimpleInventory(2){
        @Override
        public void markDirty() {
            super.markDirty();
            ConspiracyTableScreenHandler.this.onContentChanged(this);
        }
    };
    private final Inventory output = new SimpleInventory(1){
        @Override
        public void markDirty() {
            super.markDirty();
        }
    };

    public ConspiracyTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public ConspiracyTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(BackroomScreenHandlerType.CONSPIRACY_TABLE, syncId);
        this.context = context;
        this.addSlot(new Slot(input, 0, 60, 29){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.PAPER;
            }
        });

        this.addSlot(new Slot(input, 1, 98, 29){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.BLACK_DYE;
            }
        });

        this.addSlot(new Slot(output, 0, 79, 53){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);
                input.getStack(0).decrement(1);
                input.getStack(1).decrement(1);

                onUpdate();
            }
        });

        int m;
        int l;
        int offsetX = 7;

        // Inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, offsetX + l * 18, 83 + m * 18));
            }
        }

        // Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, offsetX + m * 18, 141));
        }
    }



    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        onUpdate();
    }

    public void setClientText(String text){
        this.clientText = text;

        onUpdate();
    }

    private void onUpdate(){
        ItemStack paper = this.input.getStack(0);
        ItemStack blackDye = this.input.getStack(1);
        ItemStack output = this.output.getStack(0);

        boolean validInputs = !paper.isEmpty() && !blackDye.isEmpty()
                && paper.isOf(Items.PAPER)
                && blackDye.isOf(Items.BLACK_DYE);

        DebugLogger.debug(
                validInputs,
                output.isEmpty(),
                this.clientText.length() >= 3
        );
        if (validInputs && this.clientText.length() >= 3){
            MojangUUIDFetcher.getUUID(this.clientText).thenAccept(opt -> {
                ItemStack missingPoster = BackroomItems.MISSING_POSTER.getDefaultStack();
                missingPoster.set(BackroomComponents.MISSING_POSTER_PLAYER_NAME, this.clientText);
                opt.ifPresent(uuid -> missingPoster.set(BackroomComponents.MISSING_POSTER_PLAYER_UUID, uuid.toString()));
                //this.handler.output.setStack(0, missingPoster);
                this.output.setStack(0, missingPoster);
                this.output.markDirty();
            });
        } else {
            this.output.setStack(0, ItemStack.EMPTY);
            this.output.markDirty();
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (index < 3) {
                // If the stack is in our block inventory, move it to player inventory
                if (!this.insertItem(originalStack, 3, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // If the item is in player inventory, try to put it in the correct input slot
                if (originalStack.getItem() == Items.PAPER) {
                    if (!this.insertItem(originalStack, 0, 1, false)) return ItemStack.EMPTY;
                } else if (originalStack.getItem() == Items.BLACK_DYE) {
                    if (!this.insertItem(originalStack, 1, 2, false)) return ItemStack.EMPTY;
                } else {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (player != null){
            PlayerInventory playerInventory = player.getInventory();
            if (!this.input.getStack(0).isEmpty()){
                playerInventory.insertStack(this.input.getStack(0));
            }

            if (!this.input.getStack(1).isEmpty()){
                playerInventory.insertStack(this.input.getStack(1));
            }
        }
    }
}
