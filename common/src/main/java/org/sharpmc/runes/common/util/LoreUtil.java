package org.sharpmc.runes.common.util;

import org.sharpmc.ruins.api.EnchantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoreUtil {
    public static List<String> getLore(Map<EnchantType, Integer> enchants, List<String> end) {
        List<String> lore = new ArrayList<>();

        for (Map.Entry<EnchantType, Integer> enchantEntry : enchants.entrySet()) {

            lore.add(enchantEntry.getKey().getName() + "&7 &f" + enchantEntry.getValue());

        }

        if (end != null)
            lore.addAll(end);
        return lore;

    }
}
