package org.sharpmc.runes.common;

import org.jetbrains.annotations.NotNull;
import org.sharpmc.ruins.api.Enchant;
import org.sharpmc.ruins.api.EnchantTarget;
import org.sharpmc.ruins.api.EnchantType;

public abstract class AbstractEnchant implements Enchant {

    protected final String name;
    protected final EnchantType enchantType;
    protected final EnchantTarget enchantTarget;

    public AbstractEnchant(String name, EnchantType enchantType, EnchantTarget enchantTarget) {
        this.name = name;
        this.enchantType = enchantType;
        this.enchantTarget = enchantTarget;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }


    @NotNull
    @Override
    public EnchantType getEnchantType() {
        return enchantType;
    }

    @NotNull
    @Override
    public EnchantTarget getEnchantTarget() {
        return enchantTarget;
    }
}
