package org.sharpmc.runes.common;

import org.sharpmc.ruins.api.EnchantRegistry;
import org.sharpmc.ruins.api.EnchantService;
import org.sharpmc.runes.common.enchants.boots.AntigravityEnchant;
import org.sharpmc.runes.common.enchants.chestplate.WellFedEnchant;
import org.sharpmc.runes.common.enchants.helmet.FlightEnchant;
import org.sharpmc.runes.common.enchants.helmet.GlowingEnchant;
import org.sharpmc.runes.common.enchants.leggings.SwiftEnchant;
import org.sharpmc.runes.common.enchants.sword.FrostEnchant;
import org.sharpmc.runes.common.enchants.sword.GuillotineEnchant;

import java.util.List;

public class EnchantServiceImpl implements EnchantService {

    public EnchantServiceImpl(EnchantRegistry enchantRegistry) {
        // register enchants
        List.of(
                new AntigravityEnchant(),
                new WellFedEnchant(),
                new FlightEnchant(),
                new GlowingEnchant(),
                new SwiftEnchant(),
                new FrostEnchant(),
                new GuillotineEnchant()
        ).forEach(enchant -> enchantRegistry.add(enchant.enchantType.name(), enchant));
    }
}
