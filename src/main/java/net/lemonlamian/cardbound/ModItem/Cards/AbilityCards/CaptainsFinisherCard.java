package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class CaptainsFinisherCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.captains_finisher_card.line1", "tooltip.cardbound.captains_finisher_card.line2", "tooltip.cardbound.captains_finisher_card.line3"};
    private static final double SPEED = 1.0;
    public CaptainsFinisherCard(Properties properties) {
        super(properties.stacksTo(1), 240, CardCategory.ATTACK, CardRarity.RARE, tooltipLineArray);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level.isClientSide()) return true;

        if (!level.isClientSide()) {
            Vec3 look = user.getLookAngle();
            PrimedTnt primedTnt = new PrimedTnt(level, user.getX(), user.getEyeY(), user.getZ(), user);
            primedTnt.setFuse(40);
            primedTnt.setBlockState(Blocks.BARREL.defaultBlockState());
            primedTnt.setDeltaMovement(look.x * SPEED, look.y * SPEED, look.z * SPEED);
            level.addFreshEntity(primedTnt);
            level.playSound((Player) null, user, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
            level.playSound((Player) null, primedTnt, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.BLOCKS, 1.5f, 1.0f);
            level.playSound((Player) null, primedTnt, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.5f, 1.0f);

            return true;
        }



        return false;
    }

}
