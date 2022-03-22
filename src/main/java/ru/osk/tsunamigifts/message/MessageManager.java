package ru.osk.tsunamigifts.message;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import ru.osk.tsunamigifts.TsunamiGifts;
import ru.osk.tsunamigifts.common.color.IridiumColorAPI;

public class MessageManager {

    private final Configuration config;

    public MessageManager(TsunamiGifts plugin) {
        this.config = plugin.getConfig();
    }

    public void sendMessageFromConfig(CommandSender sender, String messagePath) {
        sender.sendMessage(IridiumColorAPI.process(config.getString("messages." + messagePath)));
    }

    public void sendPermissionMessage(CommandSender sender, String permissionName) {
        sender.sendMessage(IridiumColorAPI.process(config.getString("messages.noPerms")
                .replace("{permission}", permissionName)));
    }

}
