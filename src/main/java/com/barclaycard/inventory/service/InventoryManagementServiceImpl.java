package com.barclaycard.inventory.service;

import com.barclaycard.inventory.database.InventoryDatabase;
import com.barclaycard.inventory.database.TransactionManager;
import com.barclaycard.inventory.model.Item;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class InventoryManagementServiceImpl implements InventoryManagementService {

    @Autowired
    private InventoryDatabase database;

    @Autowired
    private TransactionManager transactionManager;

    public void createItem(String name, double costPrice, double sellingPrice) {
        log.info("Creating item: " + name + " with initial cost: " + costPrice + " and selling price: " + sellingPrice);
        database.createItem(name, costPrice, sellingPrice);
    }

    public void deleteItem(String name) {
        log.info("Deleting item: " + name);
        if (database.itemExists(name)) {
            int currentInventory = database.getItemQuantity(name);
            database.updateItemQuantity(name, 0);

            Item item = database.getItem(name);
            transactionManager.updateLossOnDelete(item, currentInventory);
        } else {
            throw new RuntimeException("Item with name " + name + " does not exist.");
        }

    }

    public void updateBuy(String itemName, int quantity) {
        log.info("Updating item buy quantity for item: " + itemName + " and quantity is: " + quantity);

        if (database.itemExists(itemName)) {
            int currentInventory = database.getItemQuantity(itemName);
            database.updateItemQuantity(itemName, quantity + currentInventory);

        } else {
            throw new RuntimeException("Item with name " + itemName + " does not exist.");
        }
    }

    public void updateSell(String itemName, int quantity) {
        log.info("Updating item sell quantity for item: " + itemName + " and quantity is: " + quantity);

        if (database.itemExists(itemName)) {
            int currentInventory = database.getItemQuantity(itemName);

            if (currentInventory < quantity) {
                throw new RuntimeException("Cannot sell more than available quantity: " + currentInventory);
            }

            database.updateItemQuantity(itemName, currentInventory - quantity);

            Item item = database.getItem(itemName);
            transactionManager.updateSell(item, quantity);
        } else {
            throw new RuntimeException("Item with name " + itemName + " does not exist.");
        }

    }

    public void updateSellingPrice(String itemName, double price) {

        log.info("Updating item selling price for item: " + itemName + " and price is: " + price);

        if (database.itemExists(itemName)) {
            database.updateSellingPrice(itemName, price);
        } else {
            throw new RuntimeException("Item with name " + itemName + " does not exist.");
        }

        }
}
