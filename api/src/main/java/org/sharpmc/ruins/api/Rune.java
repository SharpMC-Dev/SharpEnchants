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

    default @NotNull ItemStack buildItem() {
        final ItemStack itemStack = new ItemStack(Material.EMERALD, 1);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(LegacyComponentSerializer.legacyAmpersand().deserialize("&a&lRUNE"));
        final PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        // apply custom rune key
        container.set(RUNE_KEY, PersistentDataType.BYTE, (byte) 0);
        getEnchants().forEach((enchantType, integer) -> container.set(enchantType.getEnchantKey(), PersistentDataType.INTEGER, integer));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
