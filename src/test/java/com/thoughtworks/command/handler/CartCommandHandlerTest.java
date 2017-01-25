package com.thoughtworks.command.handler;


import com.thoughtworks.domain.aggregate.Cart;
import com.thoughtworks.domain.command.AddItemCommand;
import com.thoughtworks.domain.command.InitializeCartCommand;
import com.thoughtworks.domain.command.handler.AddItemCommandHandler;
import com.thoughtworks.domain.event.CartInitializedEvent;
import com.thoughtworks.domain.event.ItemAddedEvent;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class CartCommandHandlerTest {

    private AggregateTestFixture cart;

    @Before
    public void setUp() {
        cart = new AggregateTestFixture(Cart.class);

    }

    @Test
    public void shouldGenerateCartInitializedEvent() {
        cart.given().
                when(new InitializeCartCommand("123")).expectEvents(new CartInitializedEvent("123"));

    }

    @Test
    public void shouldGenerateItemAddedEvent() {
        cart.registerAnnotatedCommandHandler(new AddItemCommandHandler( new GenericAggregateFactory(Cart.class), cart.getEventBus()));
        cart.given(new CartInitializedEvent("123")).
                when(new AddItemCommand("123","ABC", 4)).expectEvents(new ItemAddedEvent("123","ABC",4));

    }


}
