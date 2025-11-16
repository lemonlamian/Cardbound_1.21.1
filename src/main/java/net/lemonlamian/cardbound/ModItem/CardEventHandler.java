package net.lemonlamian.cardbound.ModItem;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.CardClasses.CardItem;
import net.lemonlamian.cardbound.ModItem.CardClasses.PassiveCardItem;
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
            if (player.getCooldowns().isOnCooldown(stack.getItem())) return;

            // add cards below
            if (stack.getItem() instanceof EnrageCard card) {
                card.onPlayerHurt(player);
            }

            if (stack.getItem() instanceof PassiveCardItem cardItem) {
                player.getCooldowns().addCooldown(cardItem, cardItem.getCooldown());
            }
        }
    }


    //add more event
}
