package com.thoughtworks.domain.command.handler;

import com.thoughtworks.domain.aggregate.Cart;
import com.thoughtworks.domain.command.AddItemCommand;
import com.thoughtworks.domain.model.Item;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.caching.NoCache;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Component;

@Component
public class AddItemCommandHandler {

    private Repository<Cart> repository;


    public AddItemCommandHandler(AggregateFactory facotory, EventBus eventBus) {
        repository = new CachingEventSourcingRepository<Cart>(facotory, (EventStore) eventBus, NoCache.INSTANCE);
    }

    @CommandHandler
    public void handle(AddItemCommand command) {
        Aggregate<Cart> cartAggregate = repository.load(command.getCartId());
        cartAggregate.execute(cart -> cart.addItem(new Item(command.getItemId(), command.getQuantity())));
    }
}
