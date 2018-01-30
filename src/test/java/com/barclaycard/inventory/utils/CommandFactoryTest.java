package com.barclaycard.inventory.utils;

import com.barclaycard.inventory.service.InventoryManagementService;
import com.barclaycard.inventory.model.Command;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommandFactoryTest {

    private CommandFactory factory;
    private InventoryManagementService ims;

    @Before
    public void setup() {
        factory = new CommandFactory();
        ims = Mockito.mock(InventoryManagementService.class);

        ReflectionTestUtils.setField(factory, null, ims, InventoryManagementService.class);
    }

    @Test
    public void createComamnd() {
        String testString = "create Book01 10.50 13.79";

        Command result = factory.getComamnd(testString);
        result.execute();

        verify(ims, times(1)).createItem("Book01", 10.50d, 13.79d);
    }

    @Test
    public void updateBuyComamnd() {
        String testString = "updateBuy Tab01 100";

        Command result = factory.getComamnd(testString);
        result.execute();

        verify(ims, times(1)).updateBuy("Tab01", 100);
    }

    @Test
    public void updateSellCommand() {
        String testString = "updateSell Food01 1";

        Command result = factory.getComamnd(testString);
        result.execute();

        verify(ims, times(1)).updateSell("Food01", 1);
    }

    @Test
    public void deleteCommand() {
        String testString = "delete Book01";

        Command result = factory.getComamnd(testString);
        result.execute();

        verify(ims, times(1)).deleteItem("Book01");
    }
}