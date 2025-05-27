package projectbackroom.jonathanx.world.nodegen;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import projectbackroom.jonathanx.ProjectBackroom;

public class StructureNode {
    protected NodeGen node;
    protected StructureTemplateManager structureManager;
    protected ChunkPos chunkPos;
    private StructureTemplate structure;
    private ServerWorld serverWorld;

    public StructureNode(NodeGen node, Identifier structureId){
        this.node = node;
        this.serverWorld = this.node.getRegion().toServerWorld();
        this.structureManager = this.serverWorld.getStructureTemplateManager();
        this.structure = structureManager.getTemplate(structureId).orElse(null);
        this.chunkPos = this.node.getChunk().getPos();
    }

    public void setStructure(){
        if (this.structure != null){
            int worldX = chunkPos.getStartX();
            int worldZ = chunkPos.getStartZ();
            BlockPos pos = new BlockPos(worldX, 6, worldZ);

            ProjectBackroom.debug(pos);
            ProjectBackroom.debug(pos.getX());
            ProjectBackroom.debug(pos.getY());
            ProjectBackroom.debug(pos.getZ());

            StructurePlacementData placementData = new StructurePlacementData()
                    .setIgnoreEntities(false);
            structure.place(this.serverWorld, pos, pos, placementData, this.serverWorld.getRandom(), 2);
        } else {
            ProjectBackroom.debug("Failed to place structure");
        }
    }
}
