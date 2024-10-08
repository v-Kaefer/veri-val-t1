package com.example;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.Test;

public class CancelaTest {

    @Test
    public void testEmitirTicketDentroDoHorario_CT01() {
        Automovel automovel = new Automovel("ABC-1234", false);
        Cancela cancela = new Cancela();
        Ticket ticket = cancela.emitirTicket(automovel);
        assertEquals("ABC-1234", ticket.getAutomovel().getPlaca());
    }

    @Test
    public void testEmitirTicketAntesDoHorario_CT02() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        Cancela cancela = new Cancela();
            cancela.emitirTicket(automovel);
    }

    @Test
    public void testEmitirTicketDepoisDoHorario_CT03() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        Cancela cancela = new Cancela();
            cancela.emitirTicket(automovel); 
    }

    @Test
    public void testProcessarSaidaDentroDoHorario_CT04() {
        Automovel automovel = new Automovel("ABC-1234", false);
        Cancela cancela = new Cancela();
        Ticket ticket = cancela.emitirTicket(automovel);
        double valorDevido = cancela.processarSaida(ticket);
        assertEquals(5.90, valorDevido, 0.01);
    }

    @Test
    public void testProcessarSaidaForaDoHorario_CT05() {
        Automovel automovel = new Automovel("XYZ-9876", false);
        Cancela cancela = new Cancela();
        Ticket ticket = cancela.emitirTicket(automovel);
            ticket.setSaida(LocalDateTime.of(2022, 1, 1, 2, 1));
            cancela.processarSaida(ticket); 
        }
    }
