package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class AwwManCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.aww_man_card.line1", "tooltip.cardbound.aww_man_card.line2", "tooltip.cardbound.aww_man_card.line3"};
    public AwwManCard(Item.Properties properties) {
        super(properties.stacksTo(1), 200, CardCategory.ATTACK, CardRarity.RARE, tooltipLineArray);
    }


    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level instanceof ServerLevel serverLevel) {
            level.explode(user, user.getX(), user.getY(), user.getZ(), 4.0f, Level.ExplosionInteraction.MOB);
            serverLevel.sendParticles(ParticleTypes.GUST_EMITTER_LARGE, user.getX(), user.getY(), user.getZ(), 5, 1, 1, 1, 0.15);

            return true;
        }

        return false;
    }

}
