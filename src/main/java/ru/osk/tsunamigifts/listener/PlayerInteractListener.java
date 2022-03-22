package ru.osk.tsunamigifts.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import ru.osk.tsunamigifts.TsunamiGifts;

public class PlayerInteractListener implements Listener {

    private final TsunamiGifts plugin;

    public PlayerInteractListener(TsunamiGifts plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND) return;

        if (!plugin.getGiftManager().isGift(player.getInventory().getItemInMainHand())) return;
        player.getInventory().setItemInMainHand(plugin.getGiftManager().getGiftItemInGift(player.getInventory().getItemInMainHand()));
        event.setCancelled(true);

    }

}
