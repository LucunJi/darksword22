package io.github.lucunji.darksword22.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.lucunji.darksword22.entity.SwordEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class SwordEntityRenderer extends EntityRenderer<SwordEntity> {
    private final ItemRenderer itemRenderer;

    public SwordEntityRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer) {
        super(renderManager);
        this.itemRenderer = itemRenderer;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void render(SwordEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();

        matrixStackIn.translate(-0.2, 1.1, 0);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(15));
        matrixStackIn.scale(1F, 1.5F, 1.5F);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(-135));
        ItemStack swordStack = Items.NETHERITE_SWORD.getDefaultInstance();
        itemRenderer.renderItem(swordStack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn,
                bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY,
                itemRenderer.getItemModelWithOverrides(swordStack, entityIn.world, null));

        matrixStackIn.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(SwordEntity entity) {
        return null;
    }
}
