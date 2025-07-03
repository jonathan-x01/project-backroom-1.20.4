package projectbackroom.jonathanx.blocks.state.pipeTypes;

import net.minecraft.util.StringIdentifiable;

public enum VerticalPipeTypes implements StringIdentifiable {
    STRAIGHT("straight"),
    PLACEHOLDER("placeholder");

    private final String name;
    VerticalPipeTypes(String name){
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
