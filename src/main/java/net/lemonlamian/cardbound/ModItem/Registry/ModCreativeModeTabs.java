package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cardbound.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CARDBOUND_TAB = CREATIVE_MODE_TABS.register("cardbound_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.cardbound")) //The language key for the title of your CreativeModeTab
            .icon(() -> ModItems.I_YEARNED_FOR_THE_MINES_CARD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {

                // Add item to the tab. For your own tabs, this method is preferred over the event




                // COMMON
                //  Attack
                //  Defence
                //  Utility
                //  Resource
                //  World
                //  Passive


                // UNCOMMON
                //  Attack
                //  Defence
                //  Utility
                output.accept(ModItems.POSITION_SWAP_CARD.get());
                //  Resource
                //  World
                //  Passive
                output.accept(ModItems.ENRAGE_CARD.get());


                // RARE
                //  Attack
                output.accept(ModItems.AWW_MAN_CARD.get());
                output.accept(ModItems.CAPTAINS_FINISHER_CARD.get());
                output.accept(ModItems.CARD_BARRAGE_CARD.get());
                //  Defence
                //  Utility
                output.accept(ModItems.RECALL_CARD.get());
                //  Resource
                //  World
                //  Passive


                // EPIC
                //  Attack
                //  Defence
                //  Utility
                output.accept(ModItems.TUNNEL_BORE_CARD.get());
                //  Resource
                //  World
                //  Passive
                output.accept(ModItems.I_YEARNED_FOR_THE_MINES_CARD.get());


                // LEGENDARY
                //  Attack
                output.accept(ModItems.WRATH_OF_ZEUS_CARD.get());
                //  Defence
                //  Utility
                //  Resource
                output.accept(ModItems.THE_PERFECT_PICKAXE_CARD.get());
                //  World
                //  Passive

                // FABLED
                //  Attack
                //  Defence
                //  Utility
                //  Resource
                //  World
                //  Passive


                // OTHERS
                // Tools & Weapons
                output.accept(ModItems.THE_PERFECT_PICKAXE_ITEM.get());

            }).build());
}
