package com.thoughtworks;

import com.thoughtworks.adapter.http.CartAPI;
import com.thoughtworks.domain.aggregate.Cart;
import com.thoughtworks.domain.command.handler.AddItemCommandHandler;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        CartAPI.class,
        Cart.class,
        AddItemCommandHandler.class,
})
@EntityScan(basePackageClasses = {DomainEventEntry.class})
public class ShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("appFanoutExchange").build();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(exchange());
    }

}
