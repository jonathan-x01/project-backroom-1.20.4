package projectbackroom.jonathanx.block.state.pipeTypes;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public interface TypedPipeSupport {
    static <T extends Enum<T> & StringIdentifiable> EnumProperty<T> build(Class<T> clazz) {
        return EnumProperty.of("type", clazz);
    }
}
