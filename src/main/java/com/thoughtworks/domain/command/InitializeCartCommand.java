package com.thoughtworks.domain.command;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class InitializeCartCommand {

    @TargetAggregateIdentifier
    private String id;

    public InitializeCartCommand(String id) {

        this.id = id;
    }


    public String getId() {
        return id;
    }
}
