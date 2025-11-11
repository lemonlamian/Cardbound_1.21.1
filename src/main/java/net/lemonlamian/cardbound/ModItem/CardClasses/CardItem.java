package net.lemonlamian.cardbound.ModItem.CardClasses;

import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public abstract class CardItem extends Item {

    protected final int cooldownTicks;
    protected final CardCategory cardCategory;
    protected final CardRarity cardRarity;

    public CardItem(Properties properties, int cooldownTicks, CardCategory cardCategory, CardRarity cardRarity) {
        super(properties);
        this.cooldownTicks = cooldownTicks;
        this.cardCategory = cardCategory;
        this.cardRarity = cardRarity;
    }

    // getter
    public int getCooldown() {return cooldownTicks;}
    public CardCategory getCategory() {return cardCategory;}
    public CardRarity getCardRarity() {return cardRarity;}


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {return InteractionResultHolder.fail(stack);}


        boolean success = onActivate(level, player, stack);

        if (success) {
            player.getCooldowns().addCooldown(this, getCooldown());
        }

        return success ? InteractionResultHolder.success(stack) : InteractionResultHolder.fail(stack);
    }


    public boolean activateExternally(Level level, LivingEntity entity, ItemStack stack) {
        return onActivate(level, entity, stack);
    }


    // for ability cards
    protected abstract boolean onActivate(Level level, LivingEntity user, ItemStack stack);



    // for passive cards
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;

        this.onPassiveTick(level, player, stack, selected);
    }

    protected void onPassiveTick(Level level, Player player, ItemStack stack, boolean selected) {}


}
