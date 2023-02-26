package org.sharpmc.ruins.api;

import me.lucko.helper.Helper;
import org.bukkit.NamespacedKey;

public enum EnchantType {

    FLIGHT("&dFlight", 1),
    GLOWING("&eGlowing", 1),
    WELL_FED("&aWell Fed", 2),
    SWIFT("&6Swift", 3),
    ANTIGRAVITY("&dAnti-Gravity", 1),
    FROST("&fFrost", 3),
    GUILLOTINE("&aGuillotine", 3);

    private final NamespacedKey enchantKey;
    private final String name;
    private final int maxLevel;

    EnchantType(String name, int maxLevel) {
        this.enchantKey = new NamespacedKey(Helper.hostPlugin(), this.name());
        this.name = name;
        this.maxLevel = maxLevel;
    }

    public NamespacedKey getEnchantKey() {
        return enchantKey;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getName() {
        return name;
    }
}
