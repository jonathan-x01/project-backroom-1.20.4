package projectbackroom.jonathanx.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.data.client.*;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.blocks.BackroomBlocks;
import projectbackroom.jonathanx.blocks.pipes.CeilingPipeBlock;
import projectbackroom.jonathanx.blocks.pipes.ConnectorPipeBlock;
import projectbackroom.jonathanx.blocks.pipes.LargeHorizontalPipeBlock;
import projectbackroom.jonathanx.blocks.pipes.VerticalPipeBlock;
import projectbackroom.jonathanx.blocks.state.pipeTypes.CeilingPipeTypes;
import projectbackroom.jonathanx.blocks.state.pipeTypes.LargeHorizontalPipeTypes;
import projectbackroom.jonathanx.blocks.state.pipeTypes.VerticalPipeTypes;

import java.util.function.BiFunction;

public class BackroomModelProvider extends FabricModelProvider {
    public BackroomModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(BackroomBlocks.ALMOND_TREE_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCross(BackroomBlocks.ALMOND_TREE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
