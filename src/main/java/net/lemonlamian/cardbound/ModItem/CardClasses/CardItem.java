package net.lemonlamian.cardbound.ModItem.CardClasses;

import net.lemonlamian.cardbound.ModItem.CardMiscClasses.CooldownHelper;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;


public abstract class CardItem extends Item {

    protected final int cooldownTicks;
    protected final CardCategory cardCategory;
    protected final CardRarity cardRarity;
    protected final String[] tooltipLineArray; // line category&lang json key

    public CardItem(Properties properties, int cooldownTicks, CardCategory cardCategory, CardRarity cardRarity, String[] tooltipLineArray) {
        super(properties);
        this.cooldownTicks = cooldownTicks;
        this.cardCategory = cardCategory;
        this.cardRarity = cardRarity;
        this.tooltipLineArray = tooltipLineArray;
    }

    // getter
    public int getCooldown() {return cooldownTicks;}
    public String[] getTooltipLineArray() {return tooltipLineArray;}
    public CardCategory getCategory() {return cardCategory;}
    public CardRarity getCardRarity() {return cardRarity;}

    @Override
    public Component getName(ItemStack stack) {
        Component nameText = super.getName(stack);

        switch (getCardRarity()) {
            case COMMON -> {nameText = nameText.copy();}
            case UNCOMMON -> {nameText = nameText.copy().withStyle(style -> style.withColor(0x63ff71));}
            case RARE -> {nameText = nameText.copy().withStyle(style -> style.withColor(0x63edff));}
            case EPIC -> {nameText = nameText.copy().withStyle(style -> style.withColor(0xfc55fc));}
            case LEGENDARY -> {nameText = nameText.copy().withStyle(style -> style.withColor(0xfcf881));}
            case FABLED -> {nameText = nameText.copy().withStyle(style -> style.withColor(0xfc4952));}
        }

        return nameText;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        String[] array = getTooltipLineArray();

        // for rarity&category
        switch (getCardRarity()) {
            case COMMON -> {tooltipComponents.add(Component.translatable(array[0]).withStyle(ChatFormatting.WHITE));}
            case UNCOMMON -> {tooltipComponents.add(Component.translatable(array[0]).withStyle(ChatFormatting.GREEN));}
            case RARE -> {tooltipComponents.add(Component.translatable(array[0]).withStyle(ChatFormatting.AQUA));}
            case EPIC -> {tooltipComponents.add(Component.translatable(array[0]).withStyle(ChatFormatting.LIGHT_PURPLE));}
            case LEGENDARY -> {tooltipComponents.add(Component.translatable(array[0]).withStyle(ChatFormatting.GOLD));}
            case FABLED -> {tooltipComponents.add(Component.translatable(array[0]).withStyle(ChatFormatting.RED));}
        }
        // for usage
        tooltipComponents.add(Component.translatable(array[1]).withStyle(ChatFormatting.WHITE));
        // for flavor text
        tooltipComponents.add(Component.translatable(array[2]).withStyle(ChatFormatting.GRAY));
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {return InteractionResultHolder.fail(stack);}


        boolean success = onActivate(level, player, stack);

        if (success) {
            CooldownHelper.addCardCooldown(player, this, getCooldown());
        }



        return success ? InteractionResultHolder.success(stack) : InteractionResultHolder.fail(stack);
    }


    public boolean activateExternally(Level level, LivingEntity entity, ItemStack stack) {
        return onActivate(level, entity, stack);
    }


    // for ability cards
    protected abstract boolean onActivate(Level level, LivingEntity user, ItemStack stack);

    public boolean onActivateOnEntityHit(Level level, LivingEntity shooter, Entity hitEntity, ItemStack stack) {
        return false;
    }

    public boolean onActivateOnBlockHit(Level level, LivingEntity shooter, BlockPos hitBlockPos, Vec3 hitPos, Vec3 dir, ItemStack stack) {
        return false;
    }


    // for passive cards
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;
        if (stack.getItem() instanceof AbilityCardItem) return;
        if (player.getCooldowns().isOnCooldown(this)) return;

        this.onPassiveTick(level, player, stack, selected);
        CooldownHelper.addCardCooldown(player, this, getCooldown());
    }

    protected void onPassiveTick(Level level, Player player, ItemStack stack, boolean selected) {}


}
