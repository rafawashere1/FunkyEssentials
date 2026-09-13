package org.funkymonks.funkyessentials.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.funkymonks.funkyessentials.FunkyEssentialsPlugin;
import org.funkymonks.funkyessentials.commons.constants.ConfigConsts;
import org.funkymonks.funkyessentials.commons.constants.PermissionConsts;
import org.funkymonks.funkyessentials.commons.managers.MessagesManager;

import java.util.Objects;

public class CommandTeleport implements CommandExecutor {

    private final MessagesManager messagesManager;

    public CommandTeleport(FunkyEssentialsPlugin plugin, MessagesManager messagesManager) {
        this.messagesManager = messagesManager;
        Objects.requireNonNull(plugin.getCommand("teleport")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player && !player.hasPermission(PermissionConsts.TELEPORT)) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.NO_PERMISSION));
            return true;
        }

        if (!(sender instanceof Player) && args.length != 2) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.TELEPORT_USAGE));
            return true;
        }

        if (sender instanceof Player && (args.length < 1 || args.length > 2)) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.TELEPORT_USAGE));
            return true;
        }

        Player target1 = Bukkit.getPlayerExact(args[0]);

        if (target1 == null) {
            sender.sendMessage(messagesManager.getPrefixedMessage(
                    ConfigConsts.PLAYER_OFFLINE,
                    Placeholder.unparsed("player", args[0])
            ));
            return true;
        }

        if (sender instanceof Player player && args.length == 1) {
            if (!player.teleport(target1)) {
                sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.TELEPORT_FAILED));
                return true;
            }

            sender.sendMessage(messagesManager.getPrefixedMessage(
                    ConfigConsts.TELEPORT_TELEPORTED_SELF,
                    Placeholder.unparsed("player", target1.getName())
            ));

            return true;
        }

        Player target2 = Bukkit.getPlayerExact(args[1]);

        if (target2 == null) {
            sender.sendMessage(messagesManager.getPrefixedMessage(
                    ConfigConsts.PLAYER_OFFLINE,
                    Placeholder.unparsed("player", args[1])
            ));
            return true;
        }

        if (!target1.teleport(target2)) {
            sender.sendMessage(messagesManager.getPrefixedMessage(ConfigConsts.TELEPORT_FAILED));
            return true;
        }

        sender.sendMessage(messagesManager.getPrefixedMessage(
                ConfigConsts.TELEPORT_TELEPORTED_OTHER,
                Placeholder.unparsed("player1", target1.getName()),
                Placeholder.unparsed("player2", target2.getName())
        ));

        return true;
    }
}
