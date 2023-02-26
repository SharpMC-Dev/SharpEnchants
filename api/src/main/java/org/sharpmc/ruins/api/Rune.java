package org.sharpmc.ruins.api;

import me.lucko.helper.Helper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Rune {

    @NotNull NamespacedKey RUNE_KEY = new NamespacedKey(Helper.hostPlugin(), "RUNE_ITEM");

    @NotNull Map<EnchantType, Integer> getEnchants();

    @NotNull ItemStack buildItem();

}
