package com.barclaycard.inventory.commands;

import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;

@Log4j2
public class FileCommandsProvider implements  CommandProvider {

    private static final String COMMANDS_FILE_LOCATION = "data/test-commands.txt";
    private FileReader fileReader;
    private BufferedReader br;

    @PostConstruct
    public void init() throws FileNotFoundException {
        fileReader = new FileReader(new File(COMMANDS_FILE_LOCATION));
        br = new BufferedReader(fileReader);
    }

    public String getNextCommand() throws Exception {
        return br.readLine();
    }

    @PreDestroy
    public void cleanUp() {
        try {
            if (fileReader != null) {
                fileReader.close();
            }

            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            // Don't throw error while closing file
            log.error("Error closing file reader resource for file: " + COMMANDS_FILE_LOCATION);
        }
    }
}
