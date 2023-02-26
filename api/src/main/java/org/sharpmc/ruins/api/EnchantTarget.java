package org.sharpmc.ruins.api;

import org.bukkit.Material;

public enum EnchantTarget {

    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    WEAPON;


    public static EnchantTarget getTarget(Material material) {

        if (material.toString().endsWith("HELMET"))
            return EnchantTarget.HELMET;
        if (material.toString().endsWith("CHESTPLATE"))
            return EnchantTarget.CHESTPLATE;
        if (material.toString().endsWith("LEGGINGS"))
            return EnchantTarget.LEGGINGS;
        if (material.toString().endsWith("BOOTS"))
            return EnchantTarget.BOOTS;
        if (material.toString().endsWith("AXE") || material.toString().endsWith("SWORD"))
            return EnchantTarget.WEAPON;
        else return null;

    }

}
