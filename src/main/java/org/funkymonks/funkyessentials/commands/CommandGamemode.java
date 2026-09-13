package org.funkymonks.funkyessentials.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.funkymonks.funkyessentials.FunkyEssentialsPlugin;
import org.funkymonks.funkyessentials.commons.constants.ConfigConsts;
import org.funkymonks.funkyessentials.commons.constants.PermissionConsts;
import org.funkymonks.funkyessentials.commons.managers.MessagesManager;

import java.util.Locale;
import java.util.Objects;

public class CommandGamemode implements CommandExecutor {

    private final MessagesManager messagesManager;

    public CommandGamemode(FunkyEssentialsPlugin plugin, MessagesManager messagesManager) {
        this.messagesManager = messagesManager;
        Objects.requireNonNull(plugin.getCommand("gamemode")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player && !player.hasPermission(PermissionConsts.GAMEMODE)) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.NO_PERMISSION));
            return true;
        }

        if (sender instanceof Player && (args.length < 1 || args.length > 2)) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.GAMEMODE_USAGE));
            return true;
        }

        if (!(sender instanceof Player) && args.length != 2) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.GAMEMODE_USAGE));
            return true;
        }

        GameMode gameMode = getGameMode(args[0]);

        if (gameMode == null) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.GAMEMODE_INVALID));
            return true;
        }

        Player target;

        if (sender instanceof Player player) {
            if (args.length == 1) {
                target = player;
            } else {
                target = Bukkit.getPlayerExact(args[1]);
            }
        } else {
            target = Bukkit.getPlayerExact(args[1]);
        }

        if (target == null) {
            String targetName = args.length == 2 ? args[1] : "";

            sender.sendMessage(messagesManager.getPrefixedMessage(
                    ConfigConsts.PLAYER_OFFLINE,
                    Placeholder.unparsed("player", targetName)
            ));

            return true;
        }

        String gameModeName = gameMode.name().toLowerCase(Locale.ROOT);

        target.setGameMode(gameMode);

        if (target.getGameMode() != gameMode) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.GAMEMODE_CHANGE_FAILED));
            return true;
        }

        target.sendMessage(messagesManager.getPrefixedMessage(
                ConfigConsts.GAMEMODE_CHANGED_SELF,
                Placeholder.unparsed("mode", gameModeName)
        ));

        if (!target.equals(sender)) {
            sender.sendMessage(messagesManager.getPrefixedMessage(
                    ConfigConsts.GAMEMODE_CHANGED_OTHER,
                    Placeholder.unparsed("player", target.getName()),
                    Placeholder.unparsed("mode", gameModeName)
            ));
        }

        return true;
    }

    private GameMode getGameMode(String mode) {
        return switch (mode.toLowerCase(Locale.ROOT)) {
            case "creative", "c", "1" -> GameMode.CREATIVE;
            case "survival", "s", "0" -> GameMode.SURVIVAL;
            case "adventure", "a", "2" -> GameMode.ADVENTURE;
            case "spectator", "sp", "3" -> GameMode.SPECTATOR;
            default -> null;
        };
    }
}
