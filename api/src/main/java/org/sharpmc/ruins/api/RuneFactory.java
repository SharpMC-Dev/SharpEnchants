package org.sharpmc.ruins.api;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface RuneFactory {

    @NotNull Rune makeRune(@NotNull Map<EnchantType, Integer> enchants);
}
