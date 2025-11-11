package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AwwManCard extends AbilityCardItem {
    public AwwManCard(Item.Properties properties) {
        super(properties.stacksTo(1), 100, CardCategory.ATTACK, CardRarity.RARE);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (!level.isClientSide()) {
            level.explode(user, user.getX(), user.getY(), user.getZ(), 3, Level.ExplosionInteraction.MOB);

        }

        return true;
    }
}
