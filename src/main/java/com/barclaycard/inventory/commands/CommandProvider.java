package com.barclaycard.inventory.commands;

public interface CommandProvider {
    /*
        Returns empty string if no commands are available. Empty string should be
        considered as end of commands
     */
    String getNextCommand() throws Exception;
}
