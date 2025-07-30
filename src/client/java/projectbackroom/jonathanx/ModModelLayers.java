package projectbackroom.jonathanx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import projectbackroom.jonathanx.init.BackroomBlocks;
import projectbackroom.jonathanx.init.BackroomFluids;
import projectbackroom.jonathanx.init.BackroomEntities;
import projectbackroom.jonathanx.render.entities.DeathmothRenderer;
import projectbackroom.jonathanx.render.entities.FacelingRenderer;
import projectbackroom.jonathanx.render.entities.HoundRenderer;
import projectbackroom.jonathanx.render.entities.SmilerRenderer;
import projectbackroom.jonathanx.render.entities.models.DeathmothModel;
import projectbackroom.jonathanx.render.entities.models.FacelingModel;
import projectbackroom.jonathanx.render.entities.models.HoundModel;
import projectbackroom.jonathanx.render.entities.models.SmilerModel;

@Environment(EnvType.CLIENT)
public class ModModelLayers implements ClientModInitializer {
    public static final EntityModelLayer FACELING =
            new EntityModelLayer(ProjectBackroom.id("faceling"), "main");

    public static final EntityModelLayer SMILER =
            new EntityModelLayer(ProjectBackroom.id("smiler"), "main");

    public static final EntityModelLayer HOUND =
            new EntityModelLayer(ProjectBackroom.id("hound"), "main");

    public static final EntityModelLayer DEATHMOTH =
            new EntityModelLayer(ProjectBackroom.id("deathmoth"), "main");

    @Override
    public void onInitializeClient() {
        register(BackroomEntities.FACELING, FacelingRenderer::new, ModModelLayers.FACELING, FacelingModel::getTexturedModelData);
        register(BackroomEntities.SMILER, SmilerRenderer::new, ModModelLayers.SMILER, SmilerModel::getTexturedModelData);
        register(BackroomEntities.HOUND, HoundRenderer::new, ModModelLayers.HOUND, HoundModel::getTexturedModelData);
        register(BackroomEntities.DEATHMOTH, DeathmothRenderer::new, ModModelLayers.DEATHMOTH, DeathmothModel::getTexturedModelData);

        BlockRenderLayerMap.INSTANCE.putBlock(BackroomBlocks.ALMOND_TREE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BackroomBlocks.ALMOND_TREE_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BackroomBlocks.ALMOND_TREE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BackroomBlocks.ALMOND_TREE_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), BackroomFluids.ALMOND_WATER, BackroomFluids.FLOWING_ALMOND_WATER, BackroomFluids.CONTAMINATED_WATER, BackroomFluids.FLOWING_CONTAMINATED_WATER);
    }

    public <E extends Entity> void register(EntityType<? extends E> entityType, EntityRendererFactory<E> entityRendererFactory, EntityModelLayer modelLayer, EntityModelLayerRegistry.TexturedModelDataProvider provider){
        EntityRendererRegistry.register(entityType, entityRendererFactory);
        EntityModelLayerRegistry.registerModelLayer(modelLayer, provider);
    }
}
