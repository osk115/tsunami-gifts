package ru.osk.tsunamigifts.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.osk.tsunamigifts.TsunamiGifts;

public class GiftCommand implements CommandExecutor {

    private final TsunamiGifts plugin;

    public GiftCommand(TsunamiGifts plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            plugin.getMessageManager().sendMessageFromConfig(sender, "console");
            return true;
        }

        if (args.length == 0) {
            plugin.getMessageManager().sendMessageFromConfig(sender, "help");
            return true;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("create")) {

            if (!player.hasPermission("tsunamigifts.use")) {
                plugin.getMessageManager().sendPermissionMessage(sender, "tsunamigifts.use");
                return true;
            }

            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                plugin.getMessageManager().sendMessageFromConfig(sender, "noItem");
                return true;
            }

            if (plugin.getGiftManager().isGift(player.getInventory().getItemInMainHand())) {
                plugin.getMessageManager().sendMessageFromConfig(sender, "giftToGift");
                return true;
            }

            player.getInventory().setItemInMainHand(
                    plugin.getGiftManager().getDefaultGift(player.getName(), player.getInventory().getItemInMainHand()));

            plugin.getMessageManager().sendMessageFromConfig(sender, "giftCreated");

            return true;

        }

        if (args[0].equalsIgnoreCase("anonymous")) {

            if (!player.hasPermission("tsunamigifts.use.anonymous")) {
                plugin.getMessageManager().sendPermissionMessage(sender, "tsunamigifts.use.anonymous");
                return true;
            }

            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                plugin.getMessageManager().sendMessageFromConfig(sender, "noItem");
                return true;
            }

            if (plugin.getGiftManager().isGift(player.getInventory().getItemInMainHand())) {
                plugin.getMessageManager().sendMessageFromConfig(sender, "giftToGift");
                return true;
            }

            player.getInventory().setItemInMainHand(
                    plugin.getGiftManager().getAnonymousGift(player.getInventory().getItemInMainHand()));

            plugin.getMessageManager().sendMessageFromConfig(sender, "giftCreated");

            return true;

        }

        plugin.getMessageManager().sendMessageFromConfig(sender, "help");

        return true;
    }

}
