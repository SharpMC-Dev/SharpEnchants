package org.sharpmc.runes.common;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;

public class AttackEnchant extends AbstractEnchant {

    private final AttackCallback callback;

    public AttackEnchant(String name, EnchantType enchantType, EnchantTarget target, AttackCallback callback) {
        super(name, enchantType, target);
        this.callback = callback;
    }

    @Override
    public void handle(@NotNull Event event, int level) {
        if (event instanceof EntityDamageByEntityEvent damageByEntityEvent) {
            if (damageByEntityEvent.getEntity() instanceof Player entity && damageByEntityEvent.getDamager() instanceof Player damager) {
                this.callback.call(entity, damager, level, damageByEntityEvent);
            }
        }
    }

    @Override
    public void onApplication(@NotNull Player player, int level) {

    }

    @Override
    public void onDismountable(@NotNull Player player) {

    }

    public static interface AttackCallback {

        void call(@NotNull Player entity, @NotNull Player attacker, int level, EntityDamageByEntityEvent event);
    }
}
