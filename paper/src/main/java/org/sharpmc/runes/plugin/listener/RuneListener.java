package org.sharpmc.runes.plugin.listener;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.lucko.helper.Events;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.*;

public class RuneListener implements TerminableModule {

    private final EnchantRegistry enchantRegistry;
    private final EnchantService enchantService;
    private final RuneService runeService;

    public RuneListener(EnchantRegistry enchantRegistry, EnchantService enchantService, RuneService runeService) {
        this.enchantRegistry = enchantRegistry;
        this.enchantService = enchantService;
        this.runeService = runeService;
    }

    @Override
    public void setup(@NotNull TerminableConsumer consumer) {

        Events.subscribe(PrepareAnvilEvent.class).handler(event -> {
            final AnvilInventory anvilInventory = event.getInventory();

            if (anvilInventory.getFirstItem() == null || anvilInventory.getSecondItem() == null) return;

            if (runeService.isRuneItem(anvilInventory.getFirstItem()) && runeService.isRuneItem(anvilInventory.getSecondItem())) {
                final Rune rune = runeService.combine(runeService.getFromItem(anvilInventory.getFirstItem()), runeService.getFromItem(anvilInventory.getSecondItem()));
                event.setResult(rune.buildItem());
                anvilInventory.setRepairCostAmount(0);
                anvilInventory.setRepairCost(3);
            }
        }).bindWith(consumer);

        Events.subscribe(InventoryClickEvent.class).handler(event -> {

            if (event.getClickedInventory() == null || event.getClickedInventory().getType() != InventoryType.PLAYER)
                return;

            if (event.getCursor() == null || event.getCurrentItem() == null) return;

            if (!runeService.isRuneItem(event.getCursor())) return;

            Rune rune = runeService.getFromItem(event.getCursor());

            EnchantTarget target = enchantRegistry.get(rune.getEnchants().keySet().stream().toList().get(0).toString()).get().getEnchantTarget();

            if (EnchantTarget.getTarget(event.getCurrentItem().getType()) != target) return;

            event.setCancelled(true);

            enchantService.applyEnchant(event.getCurrentItem(), rune);

            event.getWhoClicked().setItemOnCursor(rune.getEnchants().size() > 0 ? rune.buildItem() : null);

        }).bindWith(consumer);

        Events.subscribe(PlayerArmorChangeEvent.class).handler(event -> {

            if (event.getOldItem() != null || event.getOldItem().getType() != Material.AIR)
                enchantRegistry.value().forEach(enchant -> {
                    int level = enchantService.getEnchantLevel(event.getOldItem(), enchant.getEnchantType());

                    if (level > 0) enchant.onDismountable(event.getPlayer());
                });

            if (event.getNewItem() != null || event.getNewItem().getType() != Material.AIR)
                enchantRegistry.value().forEach(enchant -> {
                    int level = enchantService.getEnchantLevel(event.getNewItem(), enchant.getEnchantType());

                    if (level > 0) enchant.onApplication(event.getPlayer(), level);
                });

        }).bindWith(consumer);

        Events.subscribe(EntityDamageByEntityEvent.class).handler(event -> {

            if (!(event.getDamager() instanceof Player player)) return;

            ItemStack active = player.getInventory().getItemInMainHand();

            if (EnchantTarget.getTarget(active.getType()) != EnchantTarget.WEAPON) return;

            enchantRegistry.value().forEach(enchant -> {
                int level = enchantService.getEnchantLevel(active, enchant.getEnchantType());

                if (level > 0) enchant.handle(event, level);
            });

        }).bindWith(consumer);


    }
}
