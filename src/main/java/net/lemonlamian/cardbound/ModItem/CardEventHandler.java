package net.lemonlamian.cardbound.ModItem;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.Cards.PassiveCards.EnrageCard;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = Cardbound.MODID)
public class CardEventHandler {

    @SubscribeEvent
    public static void onPlayerHurt(LivingDamageEvent.Post event) {
        if (!(event.getEntity() instanceof Player player)) return;

        for (ItemStack stack : player.getInventory().items) {
            // add cards below
            if (stack.getItem() instanceof EnrageCard card) {
                card.onPlayerHurt(player);
            }

        }
    }
}
