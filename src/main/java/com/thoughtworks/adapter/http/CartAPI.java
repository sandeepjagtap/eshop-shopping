package com.thoughtworks.adapter.http;

import com.thoughtworks.domain.command.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cart")
public class CartAPI {

    private CommandGateway commandGateway;

    public CartAPI(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<Object> create() {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new InitializeCartCommand(id));
    }

    @PostMapping(path = "{cartId}/item")
    public CompletableFuture<Object> addItem(@PathVariable String cartId, @RequestBody Map<String, String> request) {
        return commandGateway.send(
                new AddItemCommand(cartId, request.get("itemId"), Integer.parseInt(request.get("quantity"))));
    }

    @DeleteMapping(path = "{cartId}/item/{itemId}")
    public CompletableFuture<Object> removeItem(@PathVariable String cartId, @PathVariable String itemId) {
        return commandGateway.send(
                new RemoveItemCommand(cartId, itemId));
    }

    @PostMapping(path = "{cartId}/shippingInfo")
    public CompletableFuture<Object> addShippingInfo(@PathVariable String cartId, @RequestBody Map<String, String> request) {
        return commandGateway.send(
                new AddShippingInformationCommand(cartId, request.get("line1"), request.get("city")));
    }

    @PostMapping(path = "{cartId}/checkout")
    public CompletableFuture<Object> checkout(@PathVariable String cartId) {
        return commandGateway.send(new CheckoutCartCommand(cartId));
    }

}
