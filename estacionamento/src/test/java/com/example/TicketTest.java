package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import org.junit.Test;

public class TicketTest {

    @Test
    public void testCriacaoTicket() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        LocalDateTime entrada = LocalDateTime.now();
        Ticket ticket = new Ticket(1, automovel, entrada);
        assertEquals(1, ticket.getId());
        assertEquals(automovel, ticket.getAutomovel());
        assertFalse(ticket.isPago());
        assertEquals(entrada, ticket.getEntrada());
    }

    @Test
    public void testSetSaida() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        LocalDateTime entrada = LocalDateTime.now();
        Ticket ticket = new Ticket(1, automovel, entrada);
        LocalDateTime saida = LocalDateTime.now().plusHours(2);
        ticket.setSaida(saida);
        assertEquals(saida, ticket.getSaida());
    }

    @Test
    public void testSetPago() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        LocalDateTime entrada = LocalDateTime.now();
        Ticket ticket = new Ticket(1, automovel, entrada);
        ticket.setPago(true);
        assertTrue(ticket.isPago());
    }
}
