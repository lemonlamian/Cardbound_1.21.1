package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.UtiltyMethods;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.Registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;


public class ThePerfectPickaxeCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.the_perfect_pickaxe_card.line1", "tooltip.cardbound.the_perfect_pickaxe_card.line2", "tooltip.cardbound.the_perfect_pickaxe_card.line3"};
    public ThePerfectPickaxeCard(Item.Properties properties) {
        super(properties.stacksTo(1), 18000, CardCategory.RESOURCE, CardRarity.LEGENDARY, tooltipLineArray);
    }


    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level instanceof ServerLevel serverLevel && user instanceof ServerPlayer serverPlayer) {
            serverPlayer.getInventory().add(generatePickaxe(serverLevel));

            return true;
        }

        return false;
    }


    private ItemStack generatePickaxe(ServerLevel level) {
        ItemStack pickaxe = new ItemStack((ItemLike) ModItems.THE_PERFECT_PICKAXE_ITEM);
        pickaxe.enchant(UtiltyMethods.getEnchant(level, Enchantments.FORTUNE), 20);


        return pickaxe;
    }
}
