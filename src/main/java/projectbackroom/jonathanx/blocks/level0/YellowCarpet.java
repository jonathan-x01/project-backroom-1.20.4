package projectbackroom.jonathanx.blocks.level0;

import projectbackroom.jonathanx.util.state.Destination;

public class YellowCarpet extends Level0Block {

    public YellowCarpet(Settings settings) {
        super(settings);
        storedDestinations.add(Destination.LEVEL_27);
        storedDestinations.add(Destination.VOID);
    }

    @Override
    public boolean shouldBeReplacedByCorruption() {
        return true;
    }
}
