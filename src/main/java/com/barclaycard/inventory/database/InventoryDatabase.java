package com.barclaycard.inventory.database;

import com.barclaycard.inventory.model.Item;

import java.util.Collection;

public interface InventoryDatabase {

    void createItem(String name, double costPrice, double sellingPrice);

    boolean itemExists(String itemName);

    int getItemQuantity(String itemName);

    void updateItemQuantity(String itemName, int quantity);

    void updateSellingPrice(String itemName, double price);

    Item getItem(String itemName);

    Collection<Item> listItems();
}
