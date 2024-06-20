package projectbackroom.jonathanx.blocks.level0Blocks;

import projectbackroom.jonathanx.state.util.Destination;

public class YellowCarpet extends Level0Block {

    public YellowCarpet(Settings settings) {
        super(settings);
        storedDestinations.add(Destination.LEVEL_27);
        storedDestinations.add(Destination.VOID);
    }
}
