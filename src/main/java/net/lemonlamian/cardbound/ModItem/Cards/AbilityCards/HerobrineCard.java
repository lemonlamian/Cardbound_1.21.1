package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.UtiltyMethods;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class HerobrineCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.herobrine_card.line1", "tooltip.cardbound.herobrine_card.line2", "tooltip.cardbound.herobrine_card.line3"};
    public HerobrineCard(Item.Properties properties) {
        super(properties.stacksTo(1), 20, CardCategory.ATTACK, CardRarity.FABLED, tooltipLineArray);
    }


    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level.isClientSide()) return true;

        if (user instanceof Player player && player instanceof ServerPlayer serverPlayer && level instanceof ServerLevel serverLevel) {
            int radius = 50;
            List<? extends LivingEntity> entities = UtiltyMethods.getEntityInRadius(serverLevel, player.position(), radius, Monster.class);

            for (LivingEntity entity : entities) {
                entity.kill();
            }

            serverPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 10));

            stack.shrink(1);

            return true;
        }

        return false;
    }
}
