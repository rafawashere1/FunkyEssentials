package org.funkymonks.funkyessentials.commons.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.funkymonks.funkyessentials.FunkyEssentialsPlugin;
import org.funkymonks.funkyessentials.commons.constants.ConfigConsts;

public class MessagesManager {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private final FunkyEssentialsPlugin plugin;

    public MessagesManager(FunkyEssentialsPlugin plugin) {
        this.plugin = plugin;
    }

    public Component getMessage(String key) {
        return getMessage(key, TagResolver.empty());
    }

    public Component getMessage(String key, TagResolver... placeholders) {
        String message = plugin.getMessagesConfig().getString(key);

        if (message == null || message.isBlank()) {
            return MINI_MESSAGE.deserialize("<red>Mensagem não encontrada: <white>" + key + "</white>");
        }

        return MINI_MESSAGE.deserialize(message, placeholders);
    }

    public Component getPrefixedMessage(String key) {
        return getPrefixedMessage(key, TagResolver.empty());
    }

    public Component getPrefixedMessage(String key, TagResolver... placeholders) {
        String prefix = plugin.getMessagesConfig().getString(ConfigConsts.PREFIX, "");
        String message = plugin.getMessagesConfig().getString(key);

        if (message == null || message.isBlank()) {
            message = "<red>Mensagem não encontrada: <white>" + key + "</white>";
        }

        return MINI_MESSAGE.deserialize(prefix + message, placeholders);
    }
}