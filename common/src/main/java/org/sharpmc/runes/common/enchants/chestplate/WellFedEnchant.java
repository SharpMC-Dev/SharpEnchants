package org.sharpmc.runes.common.enchants.chestplate;

import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.TickableEnchant;

public class WellFedEnchant extends TickableEnchant {

    public WellFedEnchant() {
        super("WellFed", EnchantType.WELL_FED, EnchantTarget.CHESTPLATE, (player, integer) -> {
            if (player.getSaturation() < 20)
                player.setSaturation(20);
        });
    }
}
