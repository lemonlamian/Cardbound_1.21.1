package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.Registry.ModDataComponents;
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
    private long deathTick = 1200;
    private static final String[] tooltipLineArray = {"tooltip.cardbound.point_of_no_return_card.line1", "tooltip.cardbound.point_of_no_return_card.line2", "tooltip.cardbound.point_of_no_return_card.line3"};
    public PointOfNoReturnCard(Item.Properties properties) {
        super(properties, 1200, CardCategory.PASSIVE, CardRarity.EPIC, tooltipLineArray);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level instanceof ServerLevel serverLevel && user instanceof ServerPlayer serverPlayer) {
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, (int) deathTick, 5));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, (int) deathTick, 5));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, (int) deathTick, 5));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, (int) deathTick, 5));


            stack.set(ModDataComponents.IS_ACTIVATED.get(), true);
            stack.set(ModDataComponents.START_TICK.get(), serverLevel.getGameTime());

            return true;
        }

        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (level.isClientSide()) return;
        boolean isActivated = stack.getOrDefault(ModDataComponents.IS_ACTIVATED.get(), false);
        long startTick = stack.getOrDefault(ModDataComponents.START_TICK.get(), 0L);
        long elapsedTick = stack.getOrDefault(ModDataComponents.ELAPSED_TICK.get(), 0L);


        if (!isActivated) return;
        if (isActivated && startTick > 0L) {
            stack.set(ModDataComponents.ELAPSED_TICK.get(), level.getGameTime() - startTick);

            if (elapsedTick >= deathTick) {
                entity.kill();
                stack.set(ModDataComponents.IS_ACTIVATED.get(), false);
                stack.set(ModDataComponents.START_TICK.get(), 0L);
                stack.set(ModDataComponents.ELAPSED_TICK.get(), 0L);
            }
        }

    }
}
