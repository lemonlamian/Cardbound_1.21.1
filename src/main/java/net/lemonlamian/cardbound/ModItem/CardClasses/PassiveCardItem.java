package net.lemonlamian.cardbound.ModItem.CardClasses;

import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PassiveCardItem extends CardItem{
    public PassiveCardItem(Properties properties, int cooldownTicks, CardCategory cardCategory, CardRarity cardRarity, String[] tooltipLineArray) {
        super(properties, cooldownTicks, cardCategory, cardRarity, tooltipLineArray);
    }


    @Override
    protected void onPassiveTick(Level level, Player player, ItemStack stack, boolean selected) {
        super.onPassiveTick(level, player, stack, selected);
    }







    // won't be used in passive cards probably
    @Override
    protected boolean onActivate(Level level, LivingEntity entity, ItemStack stack) {
        return false;
    }
}
