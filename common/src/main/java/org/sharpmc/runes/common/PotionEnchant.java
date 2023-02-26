package org.sharpmc.runes.common;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;

public class PotionEnchant extends AbstractEnchant {

    private final PotionEffectType potionEffectType;

    public PotionEnchant(String name, EnchantType enchantType, EnchantTarget target, PotionEffectType potionEffectType) {
        super(name, enchantType, target);
        this.potionEffectType = potionEffectType;
    }

    @Override
    public void handle(@NotNull Event event, int level) {}

    @Override
    public void onApplication(@NotNull Player player, int level) {
        player.addPotionEffect(new PotionEffect(potionEffectType, Integer.MAX_VALUE, (level - 1)));
    }

    @Override
    public void onDismountable(@NotNull Player player) {
        player.removePotionEffect(potionEffectType);
    }
}
