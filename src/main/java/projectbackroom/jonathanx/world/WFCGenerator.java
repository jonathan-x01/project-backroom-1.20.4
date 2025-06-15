package projectbackroom.jonathanx.world;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.Structure;

public class WFCGenerator {
    private static final int TILE_SIZE = 16;

    private final ServerWorld world;
    private final BlockPos origin;

    public WFCGenerator(ServerWorld world, BlockPos origin) {
        this.world = world;
        this.origin = origin;
    }

    public void generate(Tile[][] tileGrid) {
        for (int x = 0; x < tileGrid.length; x++) {
            for (int z = 0; z < tileGrid[0].length; z++) {
                Tile tile = tileGrid[x][z];
                if (tile != null) {
                    placeTile(tile, x, z);
                }
            }
        }
    }

    private void placeTile(Tile tile, int x, int z) {
        BlockPos pos = origin.add(x * TILE_SIZE, 0, z * TILE_SIZE);
        StructureTemplate structure = loadStructure(tile.nbtStructureId);
        if (structure != null) {
            structure.place(world, pos, pos, new StructurePlacementData(), world.getRandom(), 3);
        }
    }

    private StructureTemplate loadStructure(Identifier id) {
        return world.getStructureTemplateManager().getTemplate(id).orElse(null);
    }
}
