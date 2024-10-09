package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TicketTest {

    @Test
    public void testTicket_Formatacao() {
        LocalDateTime entrada = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String expectedFormattedEntrada = entrada.format(formatter);

        Ticket ticket = new Ticket(01, new Automovel("ABC1234", false), entrada);
        String actualFormattedEntrada = ticket.formatarEntrada();

        assertEquals(expectedFormattedEntrada, actualFormattedEntrada);
    }

    @Test
    public void testTicket_Inicializacao() {
        LocalDateTime entrada = LocalDateTime.now();
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01, automovel, entrada);

        assertEquals(01, ticket.getId());
        assertEquals(automovel, ticket.getAutomovel());
        assertEquals(entrada, ticket.getEntrada());
        assertNull(ticket.getSaida());
        assertFalse(ticket.isPago());
    }

    @Test
    public void testTicket_SetSaida() {
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusHours(2);
        Ticket ticket = new Ticket(01, new Automovel("ABC1234", false), entrada);

        ticket.setSaida(saida);
        assertEquals(saida, ticket.getSaida());
    }

    @Test
    public void testTicket_SetPago() {
        LocalDateTime entrada = LocalDateTime.now();
        Ticket ticket = new Ticket(01, new Automovel("ABC1234", false), entrada);

        ticket.setPago(true);
        assertTrue(ticket.isPago());
    }

    @Test
    public void testTicket_FormatarSaida() {
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusHours(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String expectedFormattedSaida = saida.format(formatter);

        Ticket ticket = new Ticket(01, new Automovel("ABC1234", false), entrada);
        ticket.setSaida(saida);
        String actualFormattedSaida = ticket.formatarSaida();

        assertEquals(expectedFormattedSaida, actualFormattedSaida);
    }

    @Test
    public void testTicket_FormatarSaidaComSaida() {
        LocalDateTime entrada = LocalDateTime.now();
        Ticket ticket = new Ticket(01, new Automovel("ABC1234", false), entrada);

        String actualFormattedSaida = ticket.formatarSaida();
        assertEquals("Sem saída registrada", actualFormattedSaida);
    }
}
