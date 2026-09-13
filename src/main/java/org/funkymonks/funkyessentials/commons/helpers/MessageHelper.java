package org.funkymonks.funkyessentials.commons.helpers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public final class MessageHelper {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private MessageHelper() {
    }

    public static Component parse(String message) {
        return MINI_MESSAGE.deserialize(message);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(parse(message));
    }

    public static void sendActionBarMessage(Player player, String message) {
        player.sendActionBar(parse(message));
    }
}