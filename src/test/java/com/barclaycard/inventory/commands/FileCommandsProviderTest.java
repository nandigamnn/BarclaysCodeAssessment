package com.barclaycard.inventory.commands;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class FileCommandsProviderTest {

    private static FileCommandsProvider commandsProvider;

    @BeforeClass
    public static void setup() throws Exception {
        commandsProvider = new FileCommandsProvider();
        commandsProvider.init();
    }

    @Test
    public void getNextCommand() throws Exception {
        System.out.println(new File(".").getCanonicalPath());
        System.out.println(commandsProvider.getNextCommand());
    }

    @AfterClass
    public static void cleanUp() {
        commandsProvider.cleanUp();
    }
}