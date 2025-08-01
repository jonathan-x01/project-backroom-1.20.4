package projectbackroom.jonathanx.blocks.level0;

import projectbackroom.jonathanx.util.state.Destination;

public class Wallpaper extends Level0Block {
    public Wallpaper(Settings settings) {
        super(settings);
        storedDestinations.add(Destination.LEVEL_NEG_1);
    }

    @Override
    public boolean shouldBeReplacedByCorruption() {
        return true;
    }
}
