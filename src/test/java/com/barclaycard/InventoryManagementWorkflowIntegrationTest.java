package com.barclaycard;

import com.barclaycard.inventory.commands.CommandProvider;
import com.barclaycard.inventory.commands.FileCommandsProvider;
import com.barclaycard.inventory.config.InventoryManagementConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InventoryManagementConfig.class })
public class InventoryManagementWorkflowIntegrationTest {

    @Autowired
    private InventoryManagementWorkflow workflow;

    @Before
    public void setup() throws FileNotFoundException {
        FileCommandsProvider cmdProvider = new FileCommandsProvider();
        cmdProvider.init();

        ReflectionTestUtils.setField(workflow, null, cmdProvider, CommandProvider.class);
    }

    @Test
    public void dummyTest() throws Exception {
        workflow.startWorkflow();
    }
}