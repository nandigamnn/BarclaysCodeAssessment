package com.barclaycard.inventory.database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryDatabaseImplTest {

    private InventoryDatabaseImpl database;

    @Before
    public void setup() {
        database = new InventoryDatabaseImpl();
        database.init();
    }

    @Test
    public void createItem() {
        database.createItem("book", 10, 20);

        assertTrue(database.itemExists("book"));

    }

    @Test
    public void getItem() {
        database.createItem("book", 10, 20);

        assertEquals(database.getItem("book").getBuyingPrice(), 10, 0.0001);
        assertEquals(database.getItem("book").getSellingPrice(), 20, 0.0001);

    }

    @Test
    public void getItemQuantity() {
        database.createItem("book", 10, 20);
        database.updateItemQuantity("book", 100);
        assertEquals(database.getItemQuantity("book"), 100);
    }

}