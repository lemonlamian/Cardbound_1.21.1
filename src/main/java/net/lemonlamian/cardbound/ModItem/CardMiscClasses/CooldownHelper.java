package net.lemonlamian.cardbound.ModItem.CardMiscClasses;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class CooldownHelper {
    public static void addCardCooldown(Player player, Item item, int ticks) {
        int finalTicks = ticks;

        // example
        //if (player.getInventory().contains(new ItemStack(ModItems.COOLDOWN_HALF_CARD.get()))) {finalTicks *= 0.5;}

        player.getCooldowns().addCooldown(item, finalTicks);
    }
}
