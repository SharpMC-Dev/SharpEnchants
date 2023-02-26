package org.sharpmc.runes.common.config.wrapper;

import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RuneWrapper {

    public Material material;
    public String displayName;
    public List<String> lore;

    public RuneWrapper(Material material, String displayName, List<String> lore) {
        this.material = material;
        this.displayName = displayName;
        this.lore = lore;
    }
}
