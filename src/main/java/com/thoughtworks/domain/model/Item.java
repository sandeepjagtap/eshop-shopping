package com.thoughtworks.domain.model;

public class Item {

    private String identifier;
    private int quantity;


    public Item(String identifier, int quantity) {
        this.identifier = identifier;
        this.quantity = quantity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (quantity != item.quantity) return false;
        return identifier != null ? identifier.equals(item.identifier) : item.identifier == null;

    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
