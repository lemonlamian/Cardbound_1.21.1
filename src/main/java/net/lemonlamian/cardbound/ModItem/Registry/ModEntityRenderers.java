package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Cardbound.MODID, value = Dist.CLIENT)
public class ModEntityRenderers {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NETHER_LEGION_SOLDIER.get(), WitherSkeletonRenderer::new);
        event.registerEntityRenderer(ModEntities.THE_GREATEST_KNIGHT.get(), WitherSkeletonRenderer::new);
    }
}
