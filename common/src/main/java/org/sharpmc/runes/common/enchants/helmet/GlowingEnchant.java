package org.sharpmc.runes.common.enchants.helmet;

import org.bukkit.potion.PotionEffectType;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.PotionEnchant;

public class GlowingEnchant extends PotionEnchant {

    public GlowingEnchant() {
        super("Glowing", EnchantType.GLOWING, EnchantTarget.HELMET, PotionEffectType.GLOWING);
    }
}