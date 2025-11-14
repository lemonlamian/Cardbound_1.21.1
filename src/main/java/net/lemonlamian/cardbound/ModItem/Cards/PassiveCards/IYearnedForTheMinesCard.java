package net.lemonlamian.cardbound.ModItem.Cards.PassiveCards;

import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.CardClasses.PassiveCardItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class IYearnedForTheMinesCard extends PassiveCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.i_yearned_for_the_mines_card.line1", "tooltip.cardbound.i_yearned_for_the_mines_card.line2", "tooltip.cardbound.i_yearned_for_the_mines_card.line3"};
    public IYearnedForTheMinesCard(Item.Properties properties) {
        super(properties.stacksTo(1), 20, CardCategory.PASSIVE, CardRarity.EPIC, tooltipLineArray);
    }


    @Override
    protected void onPassiveTick(Level level, Player player, ItemStack stack, boolean selected) {
        if (level.isClientSide()) return;

        if (level.getGameTime() % 20 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2));
        }
    }

}
