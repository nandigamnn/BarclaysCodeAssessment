package com.barclaycard.inventory.utils;

import com.barclaycard.inventory.database.InventoryDatabase;
import com.barclaycard.inventory.database.TransactionManager;
import com.barclaycard.inventory.model.Item;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ReportPrinter {

    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private InventoryDatabase database;

    public static final String REPORT_ENTRY_FORMAT = "%s\t%f\t%f\t%d\t%f";

    public void printReport() {
        System.out.println("\t\t\tINVENTORY REPORT\t\t\t");
        System.out.println("Item Name\tBought At\tSold At\tAvailableQty\tValue");
        System.out.println("______________________________________________________");
        double totalValue = 0;
        for (Item item : database.listItems()) {
            int availableQuantity = database.getItemQuantity(item.getItemName());
            double remainingValue = availableQuantity * item.getBuyingPrice();
            totalValue += remainingValue;
            System.out.println(String.format(REPORT_ENTRY_FORMAT, item.getItemName(), item.getBuyingPrice(), item.getSellingPrice(), availableQuantity, remainingValue));
        }

        System.out.println("______________________________________________________");
        System.out.println("Total value \t\t\t\t" + totalValue);
        System.out.println("Profit since previous report\t\t\t\t" + transactionManager.getTotalChange());

        transactionManager.clearCurrentTracking();
    }
}
