package org.sharpmc.runes.common;

import me.lucko.helper.text3.Text;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantRegistry;
import org.sharpmc.ruins.api.EnchantService;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.runes.common.enchants.boots.AntigravityEnchant;
import org.sharpmc.runes.common.enchants.chestplate.WellFedEnchant;
import org.sharpmc.runes.common.enchants.helmet.FlightEnchant;
import org.sharpmc.runes.common.enchants.helmet.GlowingEnchant;
import org.sharpmc.runes.common.enchants.leggings.SwiftEnchant;
import org.sharpmc.runes.common.enchants.sword.FrostEnchant;
import org.sharpmc.runes.common.enchants.sword.GuillotineEnchant;
import org.sharpmc.runes.common.util.LoreUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnchantServiceImpl implements EnchantService {

    public EnchantServiceImpl(EnchantRegistry enchantRegistry) {
        // register enchants
        List.of(
                new AntigravityEnchant(),
                new WellFedEnchant(),
                new FlightEnchant(),
                new GlowingEnchant(),
                new SwiftEnchant(),
                new FrostEnchant(),
                new GuillotineEnchant()
        ).forEach(enchant -> enchantRegistry.add(enchant.enchantType.name(), enchant));
    }

    @Override
    public @NotNull Map<EnchantType, Integer> getEnchants(@NotNull ItemStack item) {
        Map<EnchantType, Integer> enchants = new LinkedHashMap<>();

        for (EnchantType type : EnchantType.values()) {

            int level = getEnchantLevel(item, type);

            if (level == 0) continue;

            enchants.put(type, level);

        }

        return enchants;
    }

    @Override
    public @NotNull Map<EnchantType, Integer> getEnchants(@NotNull PersistentDataContainer container) {
        Map<EnchantType, Integer> enchants = new LinkedHashMap<>();

        for (EnchantType type : EnchantType.values()) {

            int level = container.has(type.getEnchantKey()) ? container.get(type.getEnchantKey(), PersistentDataType.INTEGER) : 0;

            if (level == 0) continue;

            enchants.put(type, level);

        }

        return enchants;
    }

    @Override
    public int getEnchantLevel(@NotNull ItemStack item, @NotNull EnchantType enchant) {

        if (!item.hasItemMeta()) return 0;

        ItemMeta meta = item.getItemMeta();

        PersistentDataContainer container = meta.getPersistentDataContainer();

        return container.has(enchant.getEnchantKey()) ? container.get(enchant.getEnchantKey(), PersistentDataType.INTEGER) : 0;
    }

    @Override
    public int applyEnchant(@NotNull ItemStack item, @NotNull EnchantType enchant, int level) {
        ItemMeta meta = item.getItemMeta();

        PersistentDataContainer container = meta.getPersistentDataContainer();

        int currentLevel = getEnchantLevel(item, enchant);

        if (currentLevel == enchant.getMaxLevel()) return level;

        int toApply = Math.min(enchant.getMaxLevel(), currentLevel + level);

        int r = level - toApply;

        container.set(enchant.getEnchantKey(), PersistentDataType.INTEGER, toApply);

        List<Component> lore = new ArrayList<>();

        for (String line : LoreUtil.getLore(getEnchants(container), null)) {
            lore.add(Component.text(Text.colorize(line)).asComponent());

        }

        meta.lore(lore);

        item.setItemMeta(meta);

        return r;
    }

    // Swift 2
    // Boots with swift 1
    //


}
