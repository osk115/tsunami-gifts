package ru.osk.tsunamigifts.gift;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.osk.tsunamigifts.TsunamiGifts;
import ru.osk.tsunamigifts.common.StringItems;
import ru.osk.tsunamigifts.common.color.IridiumColorAPI;

import java.util.regex.Pattern;

public class GiftManager {

    private static final String GIFT_ITEM_TAG = "gift::";
    private static final Material NULL_MATERIAL = Material.STONE;

    private final Gift defaultGift;
    private final Gift anonymousGift;

    public GiftManager(TsunamiGifts plugin) {

        Configuration config = plugin.getConfig();

        this.defaultGift = new Gift(
                config.getStringList("gifts.default.headValues"),
                config.getString("gifts.default.name"),
                config.getStringList("gifts.default.lore"));

        this.anonymousGift = new Gift(
                config.getStringList("gifts.anonymous.headValues"),
                config.getString("gifts.anonymous.name"),
                config.getStringList("gifts.anonymous.lore"));

    }

    public ItemStack getDefaultGift(String playerName, ItemStack itemToGift) {

        ItemStack giftItem = this.defaultGift.getGiftItem(playerName);
        ItemMeta itemMeta = giftItem.getItemMeta();

        itemMeta.setLocalizedName(GIFT_ITEM_TAG + StringItems.itemStackToString(itemToGift));
        giftItem.setItemMeta(itemMeta);

        return giftItem;

    }

    public ItemStack getAnonymousGift(ItemStack itemToGift) {

        ItemStack giftItem = this.anonymousGift.getGiftItem();
        ItemMeta itemMeta = giftItem.getItemMeta();

        itemMeta.setLocalizedName(GIFT_ITEM_TAG + StringItems.itemStackToString(itemToGift));
        giftItem.setItemMeta(itemMeta);

        return giftItem;

    }

    public boolean isGift(ItemStack itemStack) {

        if (!itemStack.hasItemMeta()) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.hasLocalizedName()) return false;

        return itemMeta.getLocalizedName().startsWith(GIFT_ITEM_TAG);

    }

    public ItemStack getGiftItemInGift(ItemStack giftItem) {

        if (!isGift(giftItem)) return new ItemStack(NULL_MATERIAL);

        String stringItem = giftItem.getItemMeta()
                .getLocalizedName().replaceFirst(Pattern.quote(GIFT_ITEM_TAG), "");

        return StringItems.stringToItemStack(stringItem);

    }

}
