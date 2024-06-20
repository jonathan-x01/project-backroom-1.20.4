package projectbackroom.jonathanx.blocks.level0Blocks;

import projectbackroom.jonathanx.state.util.Destination;

public class Wallpaper extends Level0Block {
    public Wallpaper(Settings settings) {
        super(settings);
        storedDestinations.add(Destination.LEVEL_NEG_1);
    }
}
