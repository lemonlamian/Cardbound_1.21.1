package net.lemonlamian.cardbound.ModItem.Cards.PassiveCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.PassiveCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class EnrageCard extends PassiveCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.enrage_card.line1", "tooltip.cardbound.enrage_card.line2", "tooltip.cardbound.enrage_card.line3"};
    public EnrageCard(Item.Properties properties) {
        super(properties, 20, CardCategory.PASSIVE, CardRarity.UNCOMMON, tooltipLineArray);
    }


    public void onPlayerHurt(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0));
    }
}
