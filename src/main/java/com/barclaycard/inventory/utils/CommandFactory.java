package com.barclaycard.inventory.utils;

import com.barclaycard.inventory.service.InventoryManagementService;
import com.barclaycard.inventory.model.Command;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CommandFactory {

    public static final int CMD_INDEX = 0;
    public static final int ITEM_NAME_INDEX = 1;

    @Autowired
    private InventoryManagementService inventoryManagementService;

    @Autowired
    private ReportPrinter reportPrinter;

    public Command getComamnd(String command) {
        String[] cmdValues = command.split(" ");

        switch (cmdValues[CMD_INDEX].toLowerCase()) {
            case "create":
                return () -> inventoryManagementService.createItem(cmdValues[ITEM_NAME_INDEX], Double.valueOf(cmdValues[2]), Double.valueOf(cmdValues[3]));

            case "updatebuy":
                return () -> inventoryManagementService.updateBuy(cmdValues[ITEM_NAME_INDEX], Integer.valueOf(cmdValues[2]));

            case "updatesell":
                return () -> inventoryManagementService.updateSell(cmdValues[ITEM_NAME_INDEX], Integer.valueOf(cmdValues[2]));

            case "delete":
                return () -> inventoryManagementService.deleteItem(cmdValues[ITEM_NAME_INDEX]);

            case "updateSellingPrice":
                return() -> inventoryManagementService.updateSellingPrice(cmdValues[ITEM_NAME_INDEX], Double.valueOf(cmdValues[2]));

            case "report":
                return () -> reportPrinter.printReport();

                default:
                return () -> log.error("Invalid command");
        }

    }

}
