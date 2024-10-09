package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TicketTest {
    @Test
    public void testTicketFormatting() {
        LocalDateTime entrada = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String expectedFormattedEntrada = entrada.format(formatter);

        Ticket ticket = new Ticket(01, new Automovel("ABC1234", false), entrada);
        String actualFormattedEntrada = entrada.format(formatter);

        assertEquals(expectedFormattedEntrada, actualFormattedEntrada);
    }
}