package org.sharpmc.runes.common.enchants.sword;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.AttackEnchant;

import java.util.concurrent.ThreadLocalRandom;

public class FrostEnchant extends AttackEnchant {

    public FrostEnchant() {
        super("Frost", EnchantType.FROST, EnchantTarget.WEAPON, (entity, attacker, level, event) -> {
            final double chance = switch (level) {
                case 1 -> 0.03;
                case 2 -> 0.05;
                case 3 -> 0.07;
                default -> 0.01;
            };
            if (ThreadLocalRandom.current().nextDouble() <= chance) {
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 1));
            }
        });
    }
}
