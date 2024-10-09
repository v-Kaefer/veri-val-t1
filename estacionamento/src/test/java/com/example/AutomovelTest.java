package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class AutomovelTest {

    private Automovel automovel;
    private Ticket ticket;

    @Before
    public void setUp() {
        automovel = new Automovel("ABC-1234", true);
        ticket = new Ticket(0, automovel, null);
        automovel.setTicket(ticket);
    }

    @Test
    public void testGetPlaca() {
        assertEquals("ABC-1234", automovel.getPlaca());
    }

    @Test
    public void testIsVip() {
        assertTrue(automovel.isVip());
    }

    @Test
    public void testSetAndGetTicket() {
        assertEquals(ticket, automovel.getTicket());
    }

    @Test
    public void testRealizarPagamento() {
        assertFalse(ticket.isPago());
        boolean result = automovel.realizarPagamento();
        assertTrue(result);
        assertTrue(ticket.isPago());
    }
}