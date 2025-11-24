package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PointOfNoReturnCard extends AbilityCardItem {
    // nbtに登録するあとで
    private boolean isActivated = false;
    private long startTick = 0;
    private long elapsedTick;
    private long deathTick = 1200;
    private static final String[] tooltipLineArray = {"tooltip.cardbound.point_of_no_return_card.line1", "tooltip.cardbound.point_of_no_return_card.line2", "tooltip.cardbound.point_of_no_return_card.line3"};
    public PointOfNoReturnCard(Item.Properties properties) {
        super(properties, 1300, CardCategory.PASSIVE, CardRarity.EPIC, tooltipLineArray);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level instanceof ServerLevel serverLevel && user instanceof ServerPlayer serverPlayer) {
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 5));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 5));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 5));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 5));
            isActivated = true;

            return true;
        }

        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (!isActivated) return;
        if (isActivated && startTick == 0) {
            startTick = level.getGameTime();
        }

        if (isActivated && startTick > 0) {
            elapsedTick = level.getGameTime() - startTick;
            if (elapsedTick == deathTick) {
                entity.kill();
                isActivated = false;
                startTick = 0;
            }
        }
    }
}
