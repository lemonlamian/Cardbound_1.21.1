package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.Cards.AbilityCards.*;
import net.lemonlamian.cardbound.ModItem.Cards.PassiveCards.EnrageCard;
import net.lemonlamian.cardbound.ModItem.Cards.PassiveCards.IYearnedForTheMinesCard;
import net.lemonlamian.cardbound.ModItem.Enums.ModToolTiers;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cardbound.MODID);

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));


    // cards
    public static final DeferredItem<Item> AWW_MAN_CARD = ITEMS.registerItem("aww_man_card", AwwManCard::new);
    public static final DeferredItem<Item> I_YEARNED_FOR_THE_MINES_CARD = ITEMS.registerItem("i_yearned_for_the_mines_card", IYearnedForTheMinesCard::new);
    public static final DeferredItem<Item> ENRAGE_CARD = ITEMS.registerItem("enrage_card", EnrageCard::new);
    public static final DeferredItem<Item> RECALL_CARD = ITEMS.registerItem("recall_card", RecallCard::new);
    public static final DeferredItem<Item> POSITION_SWAP_CARD = ITEMS.registerItem("position_swap_card", PositionSwapCard::new);
    public static final DeferredItem<Item> CAPTAINS_FINISHER_CARD = ITEMS.registerItem("captains_finisher_card", CaptainsFinisherCard::new);
    public static final DeferredItem<Item> WRATH_OF_ZEUS_CARD = ITEMS.registerItem("wrath_of_zeus_card", WrathOfZeusCard::new);
    public static final DeferredItem<Item> THE_PERFECT_PICKAXE_CARD = ITEMS.registerItem("the_perfect_pickaxe_card", ThePerfectPickaxeCard::new);
    public static final DeferredItem<Item> TUNNEL_BORE_CARD = ITEMS.registerItem("tunnel_bore_card", TunnelBoreCard::new);


    // tools n weapons
    public static final DeferredItem<PickaxeItem> THE_PERFECT_PICKAXE_ITEM = ITEMS.registerItem("the_perfect_pickaxe_item", (properties) -> new PickaxeItem(ModToolTiers.PERFECT, properties.stacksTo(1).rarity(Rarity.EPIC).fireResistant().attributes(PickaxeItem.createAttributes(ModToolTiers.PERFECT, 7.0f, -2.8f))));


}
