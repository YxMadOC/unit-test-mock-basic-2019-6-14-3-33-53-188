package cashregister;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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

    }

    @Test
    public void should_verify_with_process_call_with_mockito() {

    }

}
