package org.sharpmc.ruins.api;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;


public interface RuneService {

    @NotNull Map<EnchantType, Integer> parseEnchants(String enchantString, String levelString);

    @NotNull Rune combine(Rune... runes);

    @NotNull Rune getFromItem(@NotNull ItemStack itemStack);

    default boolean isRuneItem(@NotNull ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) return false;
        return itemStack.getItemMeta().getPersistentDataContainer().has(Rune.RUNE_KEY, PersistentDataType.BYTE);
    }
}
