package com.example;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CancelaTest {

    @Test
    public void testCancelaTest_EmitirTicketDentroDoHorario() {
        Automovel automovel = new Automovel("ABC-1234", false);
        Cancela cancela = new Cancela();
        Ticket ticket = cancela.emitirTicket(automovel);
        assertEquals("ABC-1234", ticket.getAutomovel().getPlaca());
    }

    @Test
    public void testCancelaTest_EmitirTicketAntesDoHorario() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        Cancela cancela = new Cancela();
            cancela.emitirTicket(automovel);
    }

    @Test
    public void testCancelaTest_EmitirTicketDepoisDoHorario() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        Cancela cancela = new Cancela();
            cancela.emitirTicket(automovel); 
    }

    @Test
    public void testCancelaTest_ProcessarSaidaDentroDoHorario() {
        Automovel automovel = new Automovel("ABC-1234", false);
        Cancela cancela = new Cancela();
        Ticket ticket = cancela.emitirTicket(automovel);
        double valorDevido = cancela.processarSaida(ticket);
        assertEquals(5.90, valorDevido, 0.01);
    }

    @Test
    public void testCancelaTest_ProcessarSaidaForaDoHorario() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        Cancela cancela = new Cancela();
        Ticket ticket = cancela.emitirTicket(automovel);
            ticket.setSaida(LocalDateTime.of(2022, 1, 1, 2, 1));
            cancela.processarSaida(ticket); 
        }
    }
