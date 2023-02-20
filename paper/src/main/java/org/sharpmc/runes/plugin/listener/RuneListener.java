package org.sharpmc.runes.plugin.listener;

import me.lucko.helper.Events;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantRegistry;
import org.sharpmc.ruins.api.EnchantService;
import org.sharpmc.ruins.api.Rune;
import org.sharpmc.ruins.api.RuneService;

import java.util.Objects;

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
            if (runeService.isRuneItem(Objects.requireNonNull(anvilInventory.getFirstItem())) && runeService.isRuneItem(Objects.requireNonNull(anvilInventory.getSecondItem()))) {
                final Rune rune = runeService.combine(runeService.getFromItem(anvilInventory.getFirstItem()), runeService.getFromItem(anvilInventory.getSecondItem()));
                event.setResult(rune.buildItem());
            }
        }).bindWith(consumer);

        Events.subscribe(EntityDamageByEntityEvent.class).handler(event -> {

        }).bindWith(consumer);
    }
}
