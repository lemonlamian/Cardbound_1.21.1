package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.CardProjectileUtility;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CardBarrageCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.card_barrage_card.line1", "tooltip.cardbound.card_barrage_card.line2", "tooltip.cardbound.card_barrage_card.line3"};
    private int count = 15;
    public CardBarrageCard(Item.Properties properties) {
        super(properties, 200, CardCategory.ATTACK, CardRarity.RARE, tooltipLineArray);
    }


    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level instanceof ServerLevel && user instanceof ServerPlayer serverPlayer) {

            for (int i = 0; i < count; i++) {
                CardProjectileUtility.shootCardProjectile(level, serverPlayer, stack, 20.0f);
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean onActivateOnEntityHit(Level level, LivingEntity shooter, Entity hitEntity, ItemStack stack) {
        if (level instanceof ServerLevel && hitEntity instanceof LivingEntity livingVictim) {
            DamageSource source = new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.ARROW));
            livingVictim.hurt(source, 5);
            livingVictim.invulnerableTime = 0;

            return true;
        }

        return false;
    }


}
