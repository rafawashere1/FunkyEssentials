package org.funkymonks.funkyessentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.funkymonks.funkyessentials.commands.CommandGamemode;
import org.funkymonks.funkyessentials.commands.CommandTeleport;
import org.funkymonks.funkyessentials.commons.constants.ConfigConsts;
import org.funkymonks.funkyessentials.commons.managers.ConfigManager;
import org.funkymonks.funkyessentials.commons.managers.MessagesManager;

import java.io.IOException;

public final class FunkyEssentialsPlugin extends JavaPlugin {

    private ConfigManager messagesConfig;
    private MessagesManager messagesManager;

    @Override
    public void onEnable() {
        if (!loadConfigs()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.messagesManager = new MessagesManager(this);

        registerCommands();

        getLogger().info("FunkyEssentials iniciado com sucesso.");
    }

    @Override
    public void onDisable() {
        getLogger().info("FunkyEssentials desligado.");
    }

    private boolean loadConfigs() {
        try {
            this.messagesConfig = new ConfigManager(this, "messages.yml");
            this.messagesConfig.validateRequiredKeys(
                    ConfigConsts.PREFIX,
                    ConfigConsts.IN_GAME_ONLY,
                    ConfigConsts.NO_PERMISSION,
                    ConfigConsts.PLAYER_OFFLINE,
                    ConfigConsts.TELEPORT_USAGE,
                    ConfigConsts.TELEPORT_TELEPORTED_SELF,
                    ConfigConsts.TELEPORT_TELEPORTED_OTHER,
                    ConfigConsts.TELEPORT_FAILED,
                    ConfigConsts.GAMEMODE_USAGE,
                    ConfigConsts.GAMEMODE_INVALID,
                    ConfigConsts.GAMEMODE_CHANGED_SELF,
                    ConfigConsts.GAMEMODE_CHANGED_OTHER,
                    ConfigConsts.GAMEMODE_CHANGE_FAILED
            );
            return true;
        } catch (IOException | InvalidConfigurationException ex) {
            getLogger().severe("Erro ao carregar arquivos de configuração: " + ex.getMessage());
            return false;
        }
    }

    private void registerCommands() {
        new CommandTeleport(this, messagesManager);
        new CommandGamemode(this, messagesManager);
    }

    public FileConfiguration getMessagesConfig() {
        return this.messagesConfig.get();
    }

    public MessagesManager getMessagesManager() {
        return this.messagesManager;
    }
}
