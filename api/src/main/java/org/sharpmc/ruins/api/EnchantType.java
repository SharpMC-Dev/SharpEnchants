package org.sharpmc.ruins.api;

import me.lucko.helper.Helper;
import org.bukkit.NamespacedKey;

public enum EnchantType {

    FLIGHT(1),
    GLOWING(1),
    WELL_FED(2),
    SWIFT(3),
    ANTIGRAVITY(1),
    FROST(3),
    GUILLOTINE(3);

    private final NamespacedKey enchantKey;
    private final int maxLevel;
    EnchantType(int maxLevel) {
        this.enchantKey = new NamespacedKey(Helper.hostPlugin(), this.name());
        this.maxLevel = maxLevel;
    }

    public NamespacedKey getEnchantKey() {
        return enchantKey;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
