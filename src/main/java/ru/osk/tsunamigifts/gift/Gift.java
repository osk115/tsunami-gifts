package ru.osk.tsunamigifts.gift;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.osk.tsunamigifts.common.SkullCreator;
import ru.osk.tsunamigifts.common.color.IridiumColorAPI;

import java.util.*;

public class Gift {

    private final List<String> headValues;
    private final String name;
    private final List<String> lore;

    public Gift(List<String> headValues, String name, List<String> lore) {
        this.headValues = headValues;
        this.name = name;
        this.lore = lore;
    }

    public ItemStack getGiftItem(String playerName) {

        ItemStack itemStack = SkullCreator.itemFromBase64(this.getRandomHeadValue());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(IridiumColorAPI.process(this.name.replace("{player_name}", playerName)));

        List<String> lore = new ArrayList<>();
        for (String line : this.lore) lore.add(IridiumColorAPI.process(line.replace("{player_name}", playerName)));
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    public ItemStack getGiftItem() {

        ItemStack itemStack = SkullCreator.itemFromBase64(this.getRandomHeadValue());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(IridiumColorAPI.process(this.name));
        itemMeta.setLore(IridiumColorAPI.process(this.lore));

        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    public String getRandomHeadValue() {
        return headValues.get(new Random().nextInt(headValues.size()));
    }

    public List<String> getHeadValues() {
        return headValues;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }
}
