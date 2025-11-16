package net.lemonlamian.cardbound.ModItem.CardMiscClasses;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class CardProjectileRenderer extends EntityRenderer<CardProjectile> {

    public CardProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    public void render(CardProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packetLight) {
        float spin = (entity.tickCount + partialTicks) * 60f;
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getXRot()));

        poseStack.mulPose(Axis.YP.rotationDegrees(spin));
        poseStack.mulPose(Axis.XP.rotationDegrees(90));

        ItemStack stack = entity.getItem();

        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.GROUND, packetLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), 0);
        poseStack.popPose();

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packetLight);
    }


    @Override
    public ResourceLocation getTextureLocation(CardProjectile cardProjectile) {
        return null;
    }
}
