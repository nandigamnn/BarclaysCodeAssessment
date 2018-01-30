package com.barclaycard;

import com.barclaycard.inventory.config.InventoryManagementConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(InventoryManagementConfig.class);
        InventoryManagementWorkflow workflow = context.getBean(InventoryManagementWorkflow.class);

        try {
            workflow.startWorkflow();
        } catch (Exception e) {
            System.err.println("Error processing commands.");
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
