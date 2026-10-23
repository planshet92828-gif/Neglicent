// IguanaClient.java - Todo el código del cliente en un solo archivo
package rul.templatemod;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class IguanaClient {
    
    public static final EntityModelLayer IGUANA_LAYER = new EntityModelLayer(
        Identifier.of(TemplateMod.MOD_ID, "iguana"), "main"
    );

    public static void register() {
        EntityModelLayerRegistry.registerModelLayer(IGUANA_LAYER, IguanaModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.IGUANA, IguanaRenderer::new);
    }

    public static class IguanaRenderState extends LivingEntityRenderState {
        public final net.minecraft.entity.AnimationState walkAnimationState = new net.minecraft.entity.AnimationState();
    }

    public static class IguanaRenderer extends MobEntityRenderer<IguanaEntity, IguanaRenderState, IguanaModel> {
        private static final Identifier TEXTURE = Identifier.of(TemplateMod.MOD_ID, "textures/entity/iguana.png");

        public IguanaRenderer(EntityRendererFactory.Context context) {
            super(context, new IguanaModel(context.getPart(IGUANA_LAYER)), 0.5F);
        }

        @Override
        public IguanaRenderState createRenderState() {
            return new IguanaRenderState();
        }

        @Override
        public Identifier getTexture(IguanaRenderState state) {
            return TEXTURE;
        }

        @Override
        public void updateRenderState(IguanaEntity entity, IguanaRenderState state, float tickDelta) {
            super.updateRenderState(entity, state, tickDelta);
            if (entity.isOnGround() && entity.getVelocity().horizontalLengthSquared() > 0.001) {
                state.walkAnimationState.startIfNotRunning(entity.age);
            } else {
                state.walkAnimationState.stop();
            }
        }
    }

    public static class IguanaModel extends EntityModel<IguanaRenderState> {
        private final ModelPart backfeet;
        private final ModelPart tail;
        private final ModelPart frontfeet;
        private final ModelPart bb_main;
        
        public IguanaModel(ModelPart root) {
            super(root);
            this.backfeet = root.getChild("backfeet");
            this.tail = root.getChild("tail");
            this.frontfeet = root.getChild("frontfeet");
            this.bb_main = root.getChild("bb_main");
        }

        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            
            ModelPartData backfeet = modelPartData.addChild("backfeet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.5F, 2.5F));
            backfeet.addChild("feet_r1", ModelPartBuilder.create().uv(8, 8).cuboid(-3.0F, -1.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 6).cuboid(-3.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, -2.5F, 0.0F, 1.5708F, 0.0F));

            ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.5F, 4.0F));
            tail.addChild("tail_r1", ModelPartBuilder.create().uv(0, 4).cuboid(-11.0F, -0.5F, -0.5F, 11.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

            ModelPartData frontfeet = modelPartData.addChild("frontfeet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.5F, -3.5F));
            frontfeet.addChild("feet_r2", ModelPartBuilder.create().uv(0, 9).cuboid(3.0F, -1.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 10).cuboid(3.0F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 3.5F, 0.0F, 1.5708F, 0.0F));

            ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
            bb_main.addChild("head_r1", ModelPartBuilder.create().uv(0, 6).cuboid(0.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -6.0F, 0.0F, 1.5708F, 0.0F));
            bb_main.addChild("body_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

            return TexturedModelData.of(modelData, 32, 32);
        }
        
        @Override
        public void setAngles(IguanaRenderState state) {
            resetModelAngles();
            
            if (state.walkAnimationState.isRunning()) {
                float time = state.age * 0.1f;
                float intensity = 1.0f;
                
                float tailMovement = (float) Math.abs(Math.sin(time)) * 0.5f * intensity;
                this.tail.pitch = tailMovement;
                
                float backLegMovement = (float) Math.abs(Math.sin(time)) * 0.4f * intensity;
                float frontLegMovement = (float) Math.abs(Math.sin(time + (float) Math.PI)) * 0.4f * intensity;
                
                this.backfeet.pitch = backLegMovement;
                this.frontfeet.pitch = frontLegMovement;
            }
        }

        private void resetModelAngles() {
            this.tail.pitch = 0.0f;
            this.tail.yaw = 0.0f;
            this.tail.roll = 0.0f;
            
            this.backfeet.pitch = 0.0f;
            this.backfeet.yaw = 0.0f;
            this.backfeet.roll = 0.0f;
            
            this.frontfeet.pitch = 0.0f;
            this.frontfeet.yaw = 0.0f;
            this.frontfeet.roll = 0.0f;
        }
        
        public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
            backfeet.render(matrices, vertexConsumer, light, overlay);
            tail.render(matrices, vertexConsumer, light, overlay);
            frontfeet.render(matrices, vertexConsumer, light, overlay);
            bb_main.render(matrices, vertexConsumer, light, overlay);
        }
    }
}