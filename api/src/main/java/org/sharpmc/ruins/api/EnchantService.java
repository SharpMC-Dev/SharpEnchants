package org.sharpmc.ruins.api;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface EnchantService {
    default @NotNull Rune applyEnchant(@NotNull ItemStack item, @NotNull Rune rune) {

        for (Map.Entry<EnchantType, Integer> entry : rune.getEnchants().entrySet()) {

            int level = applyEnchant(item, entry.getKey(), entry.getValue());

            if (level == 0) rune.getEnchants().remove(entry.getKey());
            else entry.setValue(level);

        }

        return rune;
    }

    @NotNull Map<EnchantType, Integer> getEnchants(@NotNull ItemStack item);

    @NotNull Map<EnchantType, Integer> getEnchants(@NotNull PersistentDataContainer container);

    int getEnchantLevel(@NotNull ItemStack item, @NotNull EnchantType enchant);

    int applyEnchant(@NotNull ItemStack item, @NotNull EnchantType enchant, int level);

}
