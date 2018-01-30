package com.barclaycard.inventory.model;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Getter @Setter
    private String itemName;
    @Getter @Setter
    private double buyingPrice;
    @Getter @Setter
    private double sellingPrice;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;
        return item.getItemName().equals(this.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getItemName());
    }

}
