package org.sharpmc.runes.common;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;

import java.util.*;
import java.util.function.BiConsumer;

public class TickableEnchant extends AbstractEnchant implements Runnable{

    private final Map<UUID, Integer> playing = new HashMap<>();
    private final BiConsumer<Player, Integer> biConsumer;


    public TickableEnchant(String name, EnchantType enchantType, EnchantTarget target, BiConsumer<Player, Integer> biConsumer) {
        super(name, enchantType, target);
        this.biConsumer = biConsumer;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (playing.containsKey(player.getUniqueId())) {
                biConsumer.accept(player, 1);
            }
        }
    }

    @Override
    public void handle(@NotNull Event event, int level) {}

    @Override
    public void onApplication(@NotNull Player player, int level) {
        this.playing.put(player.getUniqueId(), level);
    }

    @Override
    public void onDismountable(@NotNull Player player) {
        this.playing.remove(player.getUniqueId());
    }
}
