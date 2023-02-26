package org.sharpmc.ruins.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public interface Enchant {

    @NotNull String getName();

    @NotNull EnchantType getEnchantType();
    @NotNull EnchantTarget getEnchantTarget();

    void handle(@NotNull Event event, int level);

    void onApplication(@NotNull Player player, int level);

    void onDismountable(@NotNull Player player);
}
