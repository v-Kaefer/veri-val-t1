package com.example;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CancelaTest {

    private Cancela cancela;
    private Automovel automovel;
    private Ticket ticket;

    @Before
    public void setUp() {
        cancela = new Cancela();
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(cancela.gerarNovoId(), automovel, LocalDateTime.now());
        ticket.setPago(false);
        automovel.setTicket(ticket);
    }

    @Test
    public void testCancela_EmitirTicket() {
        Ticket newTicket = cancela.emitirTicket(automovel);
        Assert.assertNotNull(newTicket);
        Assert.assertEquals(automovel, newTicket.getAutomovel());
        Assert.assertNotNull(newTicket.getEntrada());
    }

    @Test
    public void testCancela_EmitirTicketForaDoHorario() {
        LocalTime entrada = LocalTime.of(3, 0); // Fora do horário de funcionamento

        try {
            cancela.validarHorarioFuncionamento(entrada);
            cancela.emitirTicket(automovel);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("O estacionamento está fechado. Horário de funcionamento: 08:00 até 02:00.", e.getMessage());
        }
    }

    @Test
    public void testCancela_ProcessarSaida() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(cancela.gerarNovoId(), automovel, LocalDateTime.now());
        ticket.setPago(false);
        automovel.setTicket(ticket);
        double tarifa = cancela.processarSaida(ticket);
        Assert.assertNotNull(ticket.getSaida());
        Assert.assertTrue(tarifa > 0);
    }
}
