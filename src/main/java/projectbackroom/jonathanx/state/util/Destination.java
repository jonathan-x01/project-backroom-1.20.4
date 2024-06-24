package projectbackroom.jonathanx.state.util;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public enum Destination implements StringIdentifiable {
    LEVEL_NEG_1("level_neg_1"),
    LEVEL_0("level0"),
    LEVEL_1("level1"),
    LEVEL_2("level2"),
    LEVEL_3("level3"),
    LEVEL_27("level27"),
    VOID("void");

    private final String name;
    Destination(String name){
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
