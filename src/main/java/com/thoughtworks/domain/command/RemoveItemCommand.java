package com.thoughtworks.domain.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class RemoveItemCommand {

    @TargetAggregateIdentifier
    private final String cartId;

    private final String itemId;

    public RemoveItemCommand(String cartId, String itemId) {

        this.cartId = cartId;
        this.itemId = itemId;
    }

    public String getCartId() {
        return cartId;
    }

    public String getItemId() {
        return itemId;
    }
}
