package com.thoughtworks.domain.command;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddShippingInformationCommand {
    @TargetAggregateIdentifier
    private final String cartId;

    private final String line1;
    private final String city;

    public AddShippingInformationCommand(String cartId, String line1, String city) {

        this.cartId = cartId;
        this.line1 = line1;
        this.city = city;
    }
}
