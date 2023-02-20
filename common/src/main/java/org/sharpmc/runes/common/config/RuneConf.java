package org.sharpmc.runes.common.config;

import org.bukkit.Material;
import org.sharpmc.runes.common.config.wrapper.RuneWrapper;

import java.util.List;

public class RuneConf {

    public RuneWrapper runeItem = new RuneWrapper(Material.EMERALD, "&aRune Item", List.of("&7Drag n Drop onto your armor!"));
}
