package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.Cards.AbilityCards.AwwManCard;
import net.lemonlamian.cardbound.ModItem.Cards.AbilityCards.YesWayHomeCard;
import net.lemonlamian.cardbound.ModItem.Cards.PassiveCards.EnrageCard;
import net.lemonlamian.cardbound.ModItem.Cards.PassiveCards.IYearnedForTheMinesCard;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cardbound.MODID);

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));


    public static final DeferredItem<Item> AWW_MAN_CARD = ITEMS.registerItem("aww_man_card", AwwManCard::new);
    public static final DeferredItem<Item> I_YEARNED_FOR_THE_MINES_CARD = ITEMS.registerItem("i_yearned_for_the_mines_card", IYearnedForTheMinesCard::new);
    public static final DeferredItem<Item> ENRAGE_CARD = ITEMS.registerItem("enrage_card", EnrageCard::new);
    public static final DeferredItem<Item> YES_WAY_HOME_CARD = ITEMS.registerItem("yes_way_home_card", YesWayHomeCard::new);
}
