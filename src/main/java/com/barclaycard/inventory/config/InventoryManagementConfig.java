package com.barclaycard.inventory.config;

import com.barclaycard.inventory.commands.UserCommandProvider;
import com.barclaycard.inventory.database.InventoryDatabaseImpl;
import com.barclaycard.inventory.service.InventoryManagementService;
import com.barclaycard.inventory.service.InventoryManagementServiceImpl;
import com.barclaycard.inventory.commands.CommandProvider;
import com.barclaycard.inventory.database.InventoryDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.barclaycard")
public class InventoryManagementConfig {

    @Bean
    public InventoryDatabase getInventoryDatabase() {
        return new InventoryDatabaseImpl();
    }

    @Bean
    public InventoryManagementService getInventoryManagementService() {
        return new InventoryManagementServiceImpl();
    }

    @Bean
    public CommandProvider getCommandProvider() {
        return new UserCommandProvider();
    }

}
