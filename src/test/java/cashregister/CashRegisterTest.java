package cashregister;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CashRegisterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void should_print_the_real_purchase_when_call_process() {
        Item[] items = {new Item("Apple", 1.0)};
        Purchase purchase = new Purchase(items);
        CashRegister cashRegister = new CashRegister(new Printer());

        cashRegister.process(purchase);

        assertEquals(purchase.asString(), outContent.toString());
    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        Purchase purchase = mock(Purchase.class);
        CashRegister cashRegister = new CashRegister(new Printer());
        when(purchase.asString()).thenReturn("Apple 1.0");

        cashRegister.process(purchase);

        assertEquals("Apple 1.0", outContent.toString());
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        Purchase purchase = mock(Purchase.class);
        Printer printer = mock(Printer.class);
        CashRegister cashRegister = new CashRegister(printer);

        cashRegister.process(purchase);

        verify(printer).print(purchase.asString());
    }

}
