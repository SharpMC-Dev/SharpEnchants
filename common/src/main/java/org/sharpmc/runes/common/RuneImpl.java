package org.sharpmc.runes.common;

import me.lucko.helper.Services;
import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.ruins.api.Rune;
import org.sharpmc.runes.common.config.RuneConf;
import org.sharpmc.runes.common.config.wrapper.RuneWrapper;
import org.sharpmc.runes.common.util.LoreUtil;

import java.util.Map;

public class RuneImpl implements Rune {

    private final Map<EnchantType, Integer> enchants;

    public RuneImpl(Map<EnchantType, Integer> enchants) { this.enchants = enchants; }

    @Override
    public @NotNull Map<EnchantType, Integer> getEnchants() {
        return this.enchants;
    }

    @Override
    public @NotNull ItemStack buildItem() {

        RuneWrapper conf = Services.load(RuneConf.class).runeItem;

        return ItemStackBuilder.of(Material.EMERALD).name(conf.displayName)
                .lore(LoreUtil.getLore(getEnchants(), conf.lore))
                .transformMeta(itemMeta -> {

                    final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                    // apply custom rune key
                    container.set(RUNE_KEY, PersistentDataType.BYTE, (byte) 0);
                    getEnchants().forEach((enchantType, integer) -> container.set(enchantType.getEnchantKey(), PersistentDataType.INTEGER, integer));

                }).build();

    }

}
