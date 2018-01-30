package com.barclaycard;

import com.barclaycard.inventory.commands.CommandProvider;
import com.barclaycard.inventory.service.InventoryManagementService;
import com.barclaycard.inventory.model.Command;
import com.barclaycard.inventory.utils.CommandFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class InventoryManagementWorkflow {

    @Autowired
    private InventoryManagementService inventoryService;

    @Autowired
    private CommandProvider commandProvider;

    @Autowired
    private CommandFactory cmdFactory;

    public void startWorkflow() throws Exception {
        String nextCommand;

        while(true) {
            nextCommand = commandProvider.getNextCommand();

            if (StringUtils.isEmpty(nextCommand)) {
                // End of commands. Exit
                break;
            }

            Command userCommand =  cmdFactory.getComamnd(nextCommand);
            userCommand.execute();
        }

    }
}


