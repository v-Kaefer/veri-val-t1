package com.example;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
    
    @Test
    public void testFormatarEntrada() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 1, 10, 0);
        Ticket ticket = new Ticket(1, automovel, entrada);
        assertEquals("01-10-2023 10:00", ticket.formatarEntrada());
    }

    @Test
    public void testFormatarSaida() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 1, 10, 0);
        Ticket ticket = new Ticket(1, automovel, entrada);
        LocalDateTime saida = LocalDateTime.of(2023, 10, 1, 12, 0);
        ticket.setSaida(saida);
        assertEquals("01-10-2023 12:00", ticket.formatarSaida());
    }

    @Test
    public void testFormatarSaidaSemSaidaRegistrada() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 1, 10, 0);
        Ticket ticket = new Ticket(1, automovel, entrada);
        assertEquals("Sem saída registrada", ticket.formatarSaida());
    }
}
