package com.thoughtworks.domain.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CheckoutCartCommand {

    @TargetAggregateIdentifier
    private final String cartId;

    public CheckoutCartCommand(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
}
