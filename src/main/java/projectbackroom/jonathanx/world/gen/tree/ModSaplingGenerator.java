package projectbackroom.jonathanx.world.gen.tree;

import net.minecraft.block.SaplingGenerator;
import projectbackroom.jonathanx.world.gen.features.ModConfigureFeatures;

import java.util.Optional;

public class ModSaplingGenerator {
    public static final SaplingGenerator ALMOND_TREE =
            new SaplingGenerator("almond_tree", 0f, Optional.empty(),
                    Optional.empty(),
                    Optional.of(ModConfigureFeatures.ALMOND_TREE_KEY),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());
}
