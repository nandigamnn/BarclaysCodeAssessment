package com.barclaycard.inventory.commands;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Scanner;

public class UserCommandProvider implements CommandProvider {

    public static final String EMPTY_STRING = "";
    private Scanner scanner;

    @PostConstruct
    public void init() {
        scanner = new Scanner(System.in);
    }

    public String getNextCommand() throws Exception {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        } else {
            return EMPTY_STRING;
        }
    }

    @PreDestroy
    public void cleanUp() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
