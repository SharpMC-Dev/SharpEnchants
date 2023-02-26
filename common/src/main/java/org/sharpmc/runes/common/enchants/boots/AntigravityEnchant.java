package org.sharpmc.runes.common.enchants.boots;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.AbstractEnchant;

public class AntigravityEnchant extends AbstractEnchant {

    public AntigravityEnchant() {
        super("Antigravity", EnchantType.ANTIGRAVITY, EnchantTarget.BOOTS);
    }

    @Override
    public void handle(@NotNull Event event, int level) {
        if (event instanceof EntityDamageEvent damageEvent) {
            if (damageEvent.getCause() == EntityDamageEvent.DamageCause.FALL) {
                damageEvent.setCancelled(true);
            }
        }
    }

    @Override
    public void onApplication(@NotNull Player player, int level) {

    }

    @Override
    public void onDismountable(@NotNull Player player) {

    }
}
