package com.barclaycard.inventory.service;

public interface InventoryManagementService {
    void createItem(String name, double costPrice, double sellingPrice);

    void deleteItem(String name);

    void updateBuy(String itemName, int quantity);

    void updateSell(String itemName, int quantity);

    void updateSellingPrice(String itemName, double price);
}
