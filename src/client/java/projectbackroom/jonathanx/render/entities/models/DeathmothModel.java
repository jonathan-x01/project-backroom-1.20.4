// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package projectbackroom.jonathanx.render.entities.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import projectbackroom.jonathanx.entity.hostile.DeathmothEntity;

public class DeathmothModel<T extends DeathmothEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart leftLegs;
	private final ModelPart leftLowerLegs;
	private final ModelPart rightLegs;
	private final ModelPart rightLowerLegs;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart head;
	private final ModelPart antenna;
	private final ModelPart upperAntenna;
	private final ModelPart tail;
	private final ModelPart lowerTail;
	public DeathmothModel(ModelPart root) {
		this.body = root.getChild("body");

		// Left legs
		this.leftLegs = this.body.getChild("leftLegs");
		this.leftLowerLegs = this.leftLegs.getChild("leftLowerLegs");

		// Right legs
		this.rightLegs = this.body.getChild("rightLegs");
		this.rightLowerLegs = this.rightLegs.getChild("rightLowerLegs");

		// Left wings
		this.leftWing = this.body.getChild("leftWing");
		this.rightWing = this.body.getChild("rightWing");

		// Head
		this.head = this.body.getChild("head");
		this.antenna = this.head.getChild("antenna");
		this.upperAntenna = this.antenna.getChild("upperAntenna");

		// Tail
		this.tail = this.body.getChild("tail");
		this.lowerTail = this.tail.getChild("lowerTail");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -8.0F, -3.0F, 4.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 1.0F));

		ModelPartData leftLegs = body.addChild("leftLegs", ModelPartBuilder.create().uv(0, 20).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -8.0F, -3.0F));

		ModelPartData leftLowerLegs = leftLegs.addChild("leftLowerLegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData leg_r1 = leftLowerLegs.addChild("leg_r1", ModelPartBuilder.create().uv(6, 20).cuboid(0.0F, -8.0F, -1.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		ModelPartData rightLegs = body.addChild("rightLegs", ModelPartBuilder.create().uv(2, 20).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -8.0F, -3.0F));

		ModelPartData rightLowerLegs = rightLegs.addChild("rightLowerLegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData leg_r2 = rightLowerLegs.addChild("leg_r2", ModelPartBuilder.create().uv(4, 20).cuboid(0.0F, -8.0F, -1.0F, 0.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		ModelPartData leftWing = body.addChild("leftWing", ModelPartBuilder.create().uv(0, 11).cuboid(0.0F, -5.0F, 0.0F, 6.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -4.0F, -1.5F));

		ModelPartData rightWing = body.addChild("rightWing", ModelPartBuilder.create().uv(12, 11).cuboid(-6.0F, -5.0F, 0.0F, 6.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -4.0F, -1.5F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(14, 4).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, -2.0F));

		ModelPartData antenna = head.addChild("antenna", ModelPartBuilder.create().uv(14, 8).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -2.0F, 0.2618F, 0.0F, 0.0F));

		ModelPartData upperAntenna = antenna.addChild("upperAntenna", ModelPartBuilder.create().uv(14, 10).cuboid(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.48F, 0.0F, 0.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail_r1 = tail.addChild("tail_r1", ModelPartBuilder.create().uv(14, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData lowerTail = tail.addChild("lowerTail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

		ModelPartData tail_r2 = lowerTail.addChild("tail_r2", ModelPartBuilder.create().uv(8, 20).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5585F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return this.body;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
}