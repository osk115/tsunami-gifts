package ru.osk.tsunamigifts;

import org.bukkit.plugin.java.JavaPlugin;
import ru.osk.tsunamigifts.command.GiftCommand;
import ru.osk.tsunamigifts.command.GiftCommandTabCompleter;
import ru.osk.tsunamigifts.gift.GiftManager;
import ru.osk.tsunamigifts.listener.PlayerInteractListener;
import ru.osk.tsunamigifts.message.MessageManager;

import java.io.File;

public final class TsunamiGifts extends JavaPlugin {

    private GiftManager giftManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {

        createConfig();

        this.giftManager = new GiftManager(this);
        this.messageManager = new MessageManager(this);

        this.getCommand("tsunamigifts").setExecutor(new GiftCommand(this));
        this.getCommand("tsunamigifts").setTabCompleter(new GiftCommandTabCompleter());

        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);

    }

    private void createConfig(){
        File config = new File(getDataFolder() + File.pathSeparator + "config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        reloadConfig();
    }

    public GiftManager getGiftManager() {
        return giftManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

}
