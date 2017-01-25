package com.thoughtworks.domain.command;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddItemCommand {

    @TargetAggregateIdentifier
    private final String cartId;

    private final String itemId;
    private int quantity;

    public AddItemCommand(String cartId, String itemId, int quantity) {

        this.cartId = cartId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public String getItemId() {
        return itemId;
    }


    public int getQuantity() {
        return quantity;
    }
}
