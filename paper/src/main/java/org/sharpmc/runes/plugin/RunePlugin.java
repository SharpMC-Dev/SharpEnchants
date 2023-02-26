package org.sharpmc.runes.plugin;

import co.aikar.commands.PaperCommandManager;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import org.sharpmc.ruins.api.EnchantRegistry;
import org.sharpmc.ruins.api.EnchantService;
import org.sharpmc.ruins.api.RuneFactory;
import org.sharpmc.ruins.api.RuneService;
import org.sharpmc.runes.common.EnchantRegistryImpl;
import org.sharpmc.runes.common.EnchantServiceImpl;
import org.sharpmc.runes.common.RuneFactoryImpl;
import org.sharpmc.runes.common.RuneServiceImpl;
import org.sharpmc.runes.common.config.RuneConf;
import org.sharpmc.runes.plugin.command.ACFRuneCommand;
import org.sharpmc.runes.plugin.listener.RuneListener;

public class RunePlugin extends ExtendedJavaPlugin {

    @Override
    protected void enable() {

        EnchantRegistry enchantRegistry = provideService(EnchantRegistry.class, new EnchantRegistryImpl());
        EnchantService enchantService = provideService(EnchantService.class, new EnchantServiceImpl(enchantRegistry));

        RuneService runeService = provideService(RuneService.class, new RuneServiceImpl());
        RuneFactory runeFactory = provideService(RuneFactory.class, new RuneFactoryImpl());

        RuneConf runeConf = provideService(RuneConf.class, new RuneConf());

        final PaperCommandManager commandService = provideService(PaperCommandManager.class, new PaperCommandManager(this));

        commandService.registerCommand(new ACFRuneCommand(runeFactory, runeService));

        bindModule(new RuneListener(enchantRegistry, enchantService, runeService));

    }

    @Override
    protected void disable() {

    }
}
