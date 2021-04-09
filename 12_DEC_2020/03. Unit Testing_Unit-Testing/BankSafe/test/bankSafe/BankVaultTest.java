package bankSafe;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class BankVaultTest {
    private BankVault bankVault;

    @Before
    public void setUp(){
        this.bankVault = new BankVault();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetVaultCellsUnmodifiable(){
        this.bankVault.getVaultCells().put("Unknown", new Item("Petya", "123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemToNonExistingCellWillFail() throws OperationNotSupportedException {
        Item item = new Item("Petya", "567");
        this.bankVault.addItem("nonExisting", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemToBusyCellWillFail() throws OperationNotSupportedException {
        Item item = new Item("Petya", "567");
        Item item1 = new Item("Mira", "321");
        this.bankVault.addItem("A1", item);
        this.bankVault.addItem("A1", item1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddItemWhoAlreadyExistToCellWillFail() throws OperationNotSupportedException {
        BankVault newVault = new BankVault();
        Item item = new Item("Petya", "567");
        newVault.addItem("A1", item);
        newVault.addItem("B1", item);
    }

    @Test
    public void testAddItemSuccessfully() throws OperationNotSupportedException {
        BankVault newVault = new BankVault();
        Item item = new Item("Petya", "567");
        String result = newVault.addItem("A1", item);
        assertEquals("Item:567 saved successfully!",result);
        Item petya = newVault.getVaultCells().get("A1");
        assertEquals(petya,item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeItemFromNonExistingCellWillFail() throws OperationNotSupportedException {
        BankVault newVault = new BankVault();
        Item item = new Item("Petya", "567");
        String result = newVault.addItem("A1", item);
        newVault.removeItem("nonExisting", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingItemWillFail() throws OperationNotSupportedException {
        BankVault newVault = new BankVault();
        Item item = new Item("Petya", "567");
        String result = newVault.addItem("A1", item);
        newVault.removeItem("C1", item);
    }

    @Test
    public void testRemoveSuccessfullyItem() throws OperationNotSupportedException {
        BankVault newVault = new BankVault();
        Item item = new Item("Petya", "567");
        String result = newVault.addItem("A1", item);
        assertEquals("Remove item:567 successfully!",newVault.removeItem("A1", item));
        assertNull(newVault.getVaultCells().get("A1"));
    }

}