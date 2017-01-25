package com.thoughtworks.domain.aggregate;

import com.thoughtworks.domain.command.InitializeCartCommand;
import com.thoughtworks.domain.event.CartCheckoutEvent;
import com.thoughtworks.domain.event.CartInitializedEvent;
import com.thoughtworks.domain.event.ItemAddedEvent;
import com.thoughtworks.domain.model.Item;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Cart {

    @AggregateIdentifier
    private String identifier;

    private List<Item> items;

    //needed by Axon
    public Cart() {

    }

    @CommandHandler
    public Cart(InitializeCartCommand command) {
        apply(new CartInitializedEvent(command.getId()));
    }


    public void addItem(Item item) {

        apply(new ItemAddedEvent(this.identifier, item.getIdentifier(), item.getQuantity()));
    }

    public void checkout() {
        apply(new CartCheckoutEvent(this.identifier,this.items));
    }

    @EventSourcingHandler
    public void on(CartInitializedEvent event) {

        this.identifier = event.getId();
        this.items = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(ItemAddedEvent event) {
        this.items.add(new Item(event.getItemId(), event.getQuantity()));
    }

    @EventSourcingHandler
    public void on(CartCheckoutEvent event) {
    }
}
