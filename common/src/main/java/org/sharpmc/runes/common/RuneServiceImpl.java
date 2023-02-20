package org.sharpmc.runes.common;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.*;

import java.util.*;

public class RuneServiceImpl implements RuneService {

    @Override
    public Rune combine(Rune... runes) {
        // combine all runes into one
        final Map<EnchantType, Integer> map = new HashMap<>(EnchantType.values().length);
        for (Rune rune : runes) {
            rune.getEnchants().forEach((enchantType, integer) -> {
                int oldLevel = map.getOrDefault(enchantType, 0);
                if (oldLevel >= enchantType.getMaxLevel()) return;

                map.put(enchantType, Math.min(enchantType.getMaxLevel(), oldLevel + integer));
            });
        }

        return () -> map;
    }

    @Override
    public @NotNull Rune getFromItem(@NotNull ItemStack itemStack) {
        if (!isRuneItem(itemStack)) {
            throw new NullPointerException("This itemstack is not a rune!");
        }

        final Map<EnchantType, Integer> enchants = new EnumMap<>(EnchantType.class);

        final PersistentDataContainer container = itemStack.getItemMeta().getPersistentDataContainer();

        for (EnchantType enchantType : EnchantType.values()) {
            if (container.has(enchantType.getEnchantKey(), PersistentDataType.INTEGER)) {
                enchants.put(enchantType, container.get(enchantType.getEnchantKey(), PersistentDataType.INTEGER));
            }
        }
        return () -> enchants;
    }
}
