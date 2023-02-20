package org.sharpmc.runes.common.enchants.leggings;

import org.bukkit.potion.PotionEffectType;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.PotionEnchant;

public class SwiftEnchant extends PotionEnchant {

    public SwiftEnchant() {
        super("Swift", EnchantType.SWIFT, EnchantTarget.LEGGINGS, PotionEffectType.SPEED);
    }
}
