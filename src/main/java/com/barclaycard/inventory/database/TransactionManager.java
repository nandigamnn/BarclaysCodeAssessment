package com.barclaycard.inventory.database;

import com.barclaycard.inventory.model.Item;
import lombok.NonNull;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class TransactionManager {



    private double totalChange = 0D;

    @Synchronized
    public void updateSell(@NonNull Item item, int quantity) {
        totalChange += (item.getSellingPrice() - item.getBuyingPrice()) * quantity;
    }

    @Synchronized
    public void updateLossOnDelete(@NonNull Item item, int currentInventory) {
        totalChange -= (item.getBuyingPrice() * currentInventory);
    }

    public double getTotalChange() {
        return this.totalChange;
    }

    public void clearCurrentTracking() {
        this.totalChange = 0D;
    }
}
