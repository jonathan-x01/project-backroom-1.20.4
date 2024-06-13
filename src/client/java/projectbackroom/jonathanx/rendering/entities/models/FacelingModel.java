// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package projectbackroom.jonathanx.rendering.entities.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import projectbackroom.jonathanx.rendering.entities.animations.FacelingAnimations;
import projectbackroom.jonathanx.entity.custom.FacelingEntity;

public class FacelingModel<T extends FacelingEntity> extends SinglePartEntityModel<T> {
	private final ModelPart faceling;
	private final ModelPart head;
	public FacelingModel(ModelPart root) {
		this.faceling = root.getChild("faceling");
		this.head = faceling.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData faceling = modelPartData.addChild("faceling", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = faceling.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData body = faceling.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData rightArm = faceling.addChild("rightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -22.0F, 0.0F));

		ModelPartData leftArm = faceling.addChild("leftArm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -22.0F, 0.0F));

		ModelPartData rightLeg = faceling.addChild("rightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, -12.0F, 0.0F));

		ModelPartData leftLeg = faceling.addChild("leftLeg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, -12.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(FacelingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(FacelingAnimations.FACELING_WALK, limbSwing, limbSwingAmount, 1.25f, 0.5f);
		this.updateAnimation(FacelingEntity.idleAnimationState, FacelingAnimations.FACELING_IDLE, ageInTicks,2f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		/*Head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		Body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		RightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		LeftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		RightLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		LeftLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);*/
		faceling.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return faceling;
	}
}