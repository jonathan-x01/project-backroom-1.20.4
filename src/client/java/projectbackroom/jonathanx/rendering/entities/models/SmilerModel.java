// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package projectbackroom.jonathanx.rendering.entities.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import projectbackroom.jonathanx.entity.custom.SmilerEntity;
import projectbackroom.jonathanx.rendering.entities.animations.SmilerAnimations;

public class SmilerModel<T extends SmilerEntity> extends SinglePartEntityModel<T> {
	private ModelPart face;
	public SmilerModel(ModelPart root) {
		this.face = root.getChild("face");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData face = modelPartData.addChild("face", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData face_r1 = face.addChild("face_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -39.0F, -8.0F, 0.0F, 39.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		face.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return this.face;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(SmilerAnimations.SMILER_CASUAL,limbAngle,limbDistance, 2f, 2.5f);
	}
}