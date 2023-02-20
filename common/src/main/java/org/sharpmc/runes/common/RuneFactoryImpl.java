package org.sharpmc.runes.common;

import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.ruins.api.Rune;
import org.sharpmc.ruins.api.RuneFactory;

import java.util.Map;

public class RuneFactoryImpl implements RuneFactory {

    @Override
    public @NotNull Rune makeRune(@NotNull Map<EnchantType, Integer> enchants) {
        return () -> enchants;
    }

}
