package org.sharpmc.runes.common.enchants.helmet;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.AbstractEnchant;

public class FlightEnchant extends AbstractEnchant {

    public FlightEnchant() {
        super("Flight", EnchantType.FLIGHT, EnchantTarget.HELMET);
    }

    @Override
    public void handle(@NotNull Event event, int level) {}

    @Override
    public void onApplication(@NotNull Player player, int level) {
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    @Override
    public void onDismountable(@NotNull Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }
}
