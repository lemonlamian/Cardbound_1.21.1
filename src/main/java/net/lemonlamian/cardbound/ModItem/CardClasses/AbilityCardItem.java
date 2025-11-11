package net.lemonlamian.cardbound.ModItem.CardClasses;

import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AbilityCardItem extends CardItem {
    public AbilityCardItem(Properties properties, int cooldownTicks, CardCategory cardCategory, CardRarity cardRarity) {
        super(properties, cooldownTicks, cardCategory, cardRarity);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        return false;
    }
}
