package org.sharpmc.runes.common.enchants.sword;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.AttackEnchant;

import java.util.concurrent.ThreadLocalRandom;

public class GuillotineEnchant extends AttackEnchant {

    public GuillotineEnchant() {
        super("Guillotine", EnchantType.GUILLOTINE, EnchantTarget.WEAPON, (entity, attacker, level, event) -> {
            if (entity.getHealth() > 0) return;

            final double chance = switch (level) {
                case 1 -> 0.5;
                case 2 -> 0.75;
                case 3 -> 1;
                default -> 0.01;
            };

            if (ThreadLocalRandom.current().nextDouble() <= chance) {
                final ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
                final SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
                skullMeta.setOwningPlayer(entity);
                head.setItemMeta(skullMeta);
                attacker.getInventory().addItem(head);
            }
        });
    }
}
