package org.sharpmc.runes.common;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.ruins.api.Rune;
import org.sharpmc.ruins.api.RuneService;

import java.util.*;

public class RuneServiceImpl implements RuneService {

    @Override
    public @NotNull Map<EnchantType, Integer> parseEnchants(String enchantString, String levelString) {
        Map<EnchantType, Integer> map = new LinkedHashMap<>();

        List<EnchantType> enchants = Arrays.stream(enchantString.split("\\|")).map(x -> EnchantType.valueOf(x.toUpperCase())).toList();
        List<Integer> levels = Arrays.stream(levelString.split("\\|")).map(Integer::valueOf).toList();

        for (int i = 0; i < enchants.size(); i++) {

            EnchantType type = enchants.get(i);

            if (type == null) continue;

            int level = levels.size() <= i ? 1 : Objects.requireNonNullElse(levels.get(i), 1);

            map.put(type, level);
        }

        return map;
    }

    @Override
    public Rune combine(Rune... runes) {
        // combine all runes into one
        final Map<EnchantType, Integer> map = new HashMap<>(EnchantType.values().length);
        for (Rune rune : runes) {
            rune.getEnchants().forEach((enchantType, integer) -> {
                int oldLevel = map.getOrDefault(enchantType, 0);

//                if (oldLevel >= enchantType.getMaxLevel()) return;

                map.put(enchantType, oldLevel + integer);
            });
        }

        return new RuneImpl(map);
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
        return new RuneImpl(enchants);
    }
}
