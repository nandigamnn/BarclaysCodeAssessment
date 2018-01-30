package com.barclaycard.inventory.model;

@FunctionalInterface
public interface Command {
    void execute();
}
