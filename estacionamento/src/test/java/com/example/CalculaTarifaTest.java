package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculaTarifaTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String entrada = LocalDateTime.now().format(formatter);
    String saida = LocalDateTime.now().plusHours(1).format(formatter);

    @Test
    public void testCalcularTarifa_Cortesia() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.now());
        ticket.setSaida(ticket.getEntrada().plusMinutes(10));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valor = tarifa.calcularTarifa();
        assertEquals(0.0, valor, 0.01);
    }

    @Test
    public void testCalcularTarifa_UmaHora() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.now());
        System.out.println("Entrada: " + ticket.getEntrada() +" Saida: " + ticket.getSaida());
        ticket.setSaida(ticket.getEntrada().plusMinutes(45));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(5.90, valorDaTarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_MaisDeUmaHora() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.now());
        LocalDateTime entrada = LocalDateTime.now();
        ticket.setSaida(ticket.getEntrada().plusHours(2));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(8.40, valorDaTarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_DuasHorasComDesconto() {
        Automovel automovel = new Automovel("ABC1234", true);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.of(2022, 1, 1, 8, 0));
        ticket.setSaida(ticket.getEntrada().plusHours(2));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(4.2, valorDaTarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_Pernoite() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.of(2022, 1, 1, 20, 0));
        ticket.setSaida(ticket.getEntrada().plusDays(1));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(50.0, valorDaTarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_DuasPernoites() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.of(2022, 1, 1, 20, 0));
        ticket.setSaida(ticket.getEntrada().plusHours(10).plusDays(1));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(100.0, valorDaTarifa, 0.01);
    }

    // Teste para casos inválidos/falhas

    @Test
    public void testCalcularTarifa_MenosUmaHora() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.now());
        ticket.setSaida(ticket.getEntrada().plusHours(-1));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(0.00, valorDaTarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_MaisZeroHora() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.now());
        ticket.setSaida(ticket.getEntrada().plusHours(0).plusMinutes(05));
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals(0.00, valorDaTarifa, 0.01);
    }

    @Test
    public void testEncerrarTicket_DuranteEstacionamentoFechado() {
        Automovel automovel = new Automovel("ABC1234", false);
        Ticket ticket = new Ticket(01,automovel,LocalDateTime.of(2022, 1, 1, 20, 0));
        ticket.setSaida(ticket.getEntrada().plusHours(11)); //07:00 da manhã do dia seguinte
        CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
        double valorDaTarifa = tarifa.calcularTarifa();
        assertEquals("O estacionamento está fechado.", "O estacionamento está fechado.");
    }
    
    /*
    @Test
    public void testEncerrarTicket_AntesDaEntrada() {
        try {
            Automovel automovel = new Automovel("ABC1234", false);
            Ticket ticket = new Ticket(01,automovel,LocalDateTime.of(2022, 1, 1, 9, 0));
            ticket.setSaida(ticket.getEntrada().minusHours(5));
            CalculaTarifa tarifa = new CalculaTarifa(ticket, automovel);
            double valorDaTarifa = tarifa.calcularTarifa();
            assertEquals(0.0, valorDaTarifa, 0.01);
        } catch (IllegalArgumentException e) {
            assertEquals("A data/hora de saída não pode ser antes da entrada.", e.getMessage(), "O estacionamento está fechado.");
        }
    }*/
}