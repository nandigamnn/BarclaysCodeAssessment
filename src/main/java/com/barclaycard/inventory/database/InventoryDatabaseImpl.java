package com.barclaycard.inventory.database;

import com.barclaycard.inventory.model.Item;
import lombok.Synchronized;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InventoryDatabaseImpl implements InventoryDatabase {

    // Maps item to available service
    private Map<String, Integer> inMemoryDatabase;
    private Map<String, Item> nameToItemDb;

    @PostConstruct
    public void init() {
        inMemoryDatabase = new HashMap<>();

        nameToItemDb = new HashMap<>();
    }

    @Override
    @Synchronized
    public void createItem(String name, double costPrice, double sellingPrice) {
        inMemoryDatabase.put(name, 0);
        nameToItemDb.put(name, Item.builder().itemName(name).buyingPrice(costPrice).sellingPrice(sellingPrice).build());
    }

    @Override
    public boolean itemExists(String itemName) {
        return inMemoryDatabase.containsKey(itemName);
    }

    @Override
    public Item getItem(String itemName) {
        return nameToItemDb.get(itemName);
    }

    @Override
    public int getItemQuantity(String itemName) {
        return inMemoryDatabase.get(itemName);
    }

    @Override
    @Synchronized
    public void updateSellingPrice(String itemName, double price){

        Item item = nameToItemDb.get(itemName);
        item.setSellingPrice(price);

        nameToItemDb.put(itemName, item);
    }

    @Override
    @Synchronized
    public void updateItemQuantity(String itemName, int quantity) {
        inMemoryDatabase.put(itemName, quantity);
    }

    @Override
    public Collection<Item> listItems() {
        return nameToItemDb.values();
    }
}
