package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardEntities.NetherLegionSoldier;
import net.lemonlamian.cardbound.ModItem.CardEntities.TheGreatestKnight;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.Registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VestigeOfTheGreatestKnightCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.vestige_of_the_greatest_knight_card.line1", "tooltip.cardbound.vestige_of_the_greatest_knight_card.line2", "tooltip.cardbound.vestige_of_the_greatest_knight_card.line3"};
    public VestigeOfTheGreatestKnightCard(Item.Properties properties) {
        super(properties, 6000, CardCategory.ATTACK, CardRarity.EPIC, tooltipLineArray);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level.isClientSide()) return true;

        if (user instanceof Player player && player instanceof ServerPlayer serverPlayer && level instanceof ServerLevel serverLevel) {
            TheGreatestKnight knight = ModEntities.THE_GREATEST_KNIGHT.get().create(serverLevel);
            knight.setHealth(100);
            knight.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 3));
            knight.moveTo(serverPlayer.position());
            level.addFreshEntity(knight);

            return true;
        }

        return false;
    }
}
