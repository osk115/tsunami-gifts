package ru.osk.tsunamigifts.common;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class StringItems {

    public static String itemStackToString(ItemStack itemStack) {

        YamlConfiguration config = new YamlConfiguration();
        config.set("i", itemStack);
        return serialize(config.saveToString());

    }

    public static ItemStack stringToItemStack(String str) {

        YamlConfiguration config = new YamlConfiguration();

        try {
            config.loadFromString(deserialize(str));
            return config.getItemStack("i");
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            return new ItemStack(Material.STONE);
        }

    }

    private static String serialize(String toSerialize) {
        StringBuilder serialized = new StringBuilder();
        for (char c : toSerialize.toCharArray()) serialized.append((int) c).append(" ");
        return serialized.toString();
    }

    private static String deserialize(String toDeserialize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : toDeserialize.split(" ")) stringBuilder.append(Character.toChars(Integer.parseInt(s)));
        return stringBuilder.toString();
    }

}
