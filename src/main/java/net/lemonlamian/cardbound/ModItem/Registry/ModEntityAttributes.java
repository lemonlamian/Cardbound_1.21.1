package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.CardEntities.NetherLegionSoldier;
import net.lemonlamian.cardbound.ModItem.CardEntities.TheGreatestKnight;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Cardbound.MODID)
public class ModEntityAttributes {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NETHER_LEGION_SOLDIER.get(), NetherLegionSoldier.createAttributes().build());
        event.put(ModEntities.THE_GREATEST_KNIGHT.get(), TheGreatestKnight.createAttributes().build());
    }
}
