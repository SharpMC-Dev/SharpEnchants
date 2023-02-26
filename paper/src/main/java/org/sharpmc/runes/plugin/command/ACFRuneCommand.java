package org.sharpmc.runes.plugin.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.sharpmc.ruins.api.EnchantType;
import org.sharpmc.ruins.api.RuneFactory;
import org.sharpmc.ruins.api.RuneService;

import java.util.Map;
import java.util.Objects;

@CommandAlias("giverune|giverunes")
public class ACFRuneCommand extends BaseCommand {

    private final RuneFactory runeFactory;
    private final RuneService runeService;

    public ACFRuneCommand(RuneFactory runeFactory, RuneService runeService) {
        this.runeFactory = runeFactory;
        this.runeService = runeService;
    }

    @Default
    @CommandPermission("ruins.give")
    public void onDefault(CommandSender sender, OfflinePlayer player, String enchants, String levels) {

        if (!player.isOnline()) return;

        Map<EnchantType, Integer> map = runeService.parseEnchants(enchants, levels);

        Objects.requireNonNull(player.getPlayer()).getInventory().addItem(runeFactory.makeRune(map).buildItem());


    }

}
