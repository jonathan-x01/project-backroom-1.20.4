// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package projectbackroom.jonathanx.rendering.entities.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import projectbackroom.jonathanx.entity.hostile.HoundEntity;
import projectbackroom.jonathanx.rendering.entities.animations.HoundAnimations;

public class HoundModel<T extends HoundEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart backLegRight;
	private final ModelPart backLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart frontLegLeft;
	public HoundModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.backLegRight = this.body.getChild("backLegRight");
		this.backLegLeft = this.body.getChild("backLegLeft");
		this.frontLegRight = this.body.getChild("frontLegRight");
		this.frontLegLeft = this.body.getChild("frontLegLeft");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -14.5F, -4.0F, 7.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 8.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 11).cuboid(-15.0F, -6.0F, -8.0F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -10.0F, 4.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(0, 24).cuboid(-5.0F, -6.0F, -8.0F, 5.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -8.0F, 4.0F, 0.0F, 0.0F, 0.1309F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(26, 24).cuboid(-5.0F, -6.0F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-12.0F, -13.0F, 0.0F));

		ModelPartData backLegRight = body.addChild("backLegRight", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -10.0F, 3.0F));

		ModelPartData cube_r3 = backLegRight.addChild("cube_r3", ModelPartBuilder.create().uv(28, 11).cuboid(0.0F, -2.0F, 0.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 6.0F, -1.0F, 0.0F, 0.0F, -0.9425F));

		ModelPartData cube_r4 = backLegRight.addChild("cube_r4", ModelPartBuilder.create().uv(30, 0).cuboid(0.0F, -2.0F, 0.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 9.0F, -1.0F, 0.0F, 0.0F, -1.8151F));

		ModelPartData cube_r5 = backLegRight.addChild("cube_r5", ModelPartBuilder.create().uv(29, 19).cuboid(1.0F, -2.0F, 0.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 11.0F, -1.0F, 0.0F, 0.0F, -0.5236F));

		ModelPartData backLegLeft = body.addChild("backLegLeft", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -10.0F, -3.0F));

		ModelPartData cube_r6 = backLegLeft.addChild("cube_r6", ModelPartBuilder.create().uv(28, 15).cuboid(0.0F, -2.0F, -2.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 6.0F, 1.0F, 0.0F, 0.0F, -0.9425F));

		ModelPartData cube_r7 = backLegLeft.addChild("cube_r7", ModelPartBuilder.create().uv(26, 35).cuboid(0.0F, -2.0F, -2.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 9.0F, 1.0F, 0.0F, 0.0F, -1.8151F));

		ModelPartData cube_r8 = backLegLeft.addChild("cube_r8", ModelPartBuilder.create().uv(31, 4).cuboid(1.0F, -2.0F, -2.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 11.0F, 1.0F, 0.0F, 0.0F, -0.5236F));

		ModelPartData frontLegRight = body.addChild("frontLegRight", ModelPartBuilder.create(), ModelTransform.pivot(-11.0F, -9.0F, 3.0F));

		ModelPartData cube_r9 = frontLegRight.addChild("cube_r9", ModelPartBuilder.create().uv(1, 38).cuboid(1.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 8.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r10 = frontLegRight.addChild("cube_r10", ModelPartBuilder.create().uv(28, 39).cuboid(-1.0F, 4.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		ModelPartData frontLegLeft = body.addChild("frontLegLeft", ModelPartBuilder.create(), ModelTransform.pivot(-11.0F, -9.0F, -3.0F));

		ModelPartData cube_r11 = frontLegLeft.addChild("cube_r11", ModelPartBuilder.create().uv(15, 39).cuboid(1.0F, 0.0F, -2.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 8.0F, 1.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r12 = frontLegLeft.addChild("cube_r12", ModelPartBuilder.create().uv(36, 39).cuboid(-1.0F, 4.0F, -2.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 1.0F, 0.0F, 0.0F, 0.1745F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return this.body;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);

		this.animateMovement(HoundAnimations.WALKING, limbAngle, limbDistance, 1.25f, 2.5f);
	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
}