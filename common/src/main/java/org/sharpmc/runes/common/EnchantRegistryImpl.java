package org.sharpmc.runes.common;

import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.Enchant;
import org.sharpmc.ruins.api.EnchantRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EnchantRegistryImpl implements EnchantRegistry {

    private final Map<String, Enchant> enchants = new HashMap<>();

    @Override
    public void add(@NotNull String key, @NotNull Enchant type) {
        this.enchants.put(key,  type);
    }

    @Override
    public void remove(@NotNull String key) {
        this.enchants.remove(key);
    }

    @Override
    public @NotNull Optional<Enchant> get(@NotNull String key) {
        return Optional.ofNullable(this.enchants.get(key));
    }

    @Override
    public @NotNull Collection<String> keys() {
        return this.enchants.keySet();
    }

    @Override
    public @NotNull Collection<Enchant> value() {
        return this.enchants.values();
    }
}
