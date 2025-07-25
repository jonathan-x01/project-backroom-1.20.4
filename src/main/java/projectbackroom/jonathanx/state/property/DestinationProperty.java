package projectbackroom.jonathanx.state.property;

import com.google.common.collect.Lists;
import net.minecraft.state.property.EnumProperty;
import projectbackroom.jonathanx.state.util.Destination;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DestinationProperty {
    protected DestinationProperty(String name, Collection<Destination> values) {

    }

    public static DestinationProperty of(String name){
        return DestinationProperty.of(name, (Destination destination) -> true);
    }

    public static DestinationProperty of(String name, Predicate<Destination> filter) {
        return DestinationProperty.of(name, Arrays.stream(Destination.values()).filter(filter).collect(Collectors.toList()));
    }

    public static DestinationProperty of(String name, Destination ... values) {
        return DestinationProperty.of(name, Lists.newArrayList(values));
    }

    public static DestinationProperty of(String name, Collection<Destination> values) {
        return new DestinationProperty(name, values);
    }
}
