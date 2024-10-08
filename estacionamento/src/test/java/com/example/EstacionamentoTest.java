package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EstacionamentoTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String entrada = LocalDateTime.now().format(formatter);
    String saida = LocalDateTime.now().plusHours(1).format(formatter);

    @Test
    public void testCalcularTarifa_Cortesia() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusMinutes(10);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(0.0, tarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_Pernoite() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 20, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 1, 2, 10, 0);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(50.0, tarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_Hora() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusMinutes(45);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(5.90, tarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_MaisDeUmaHora() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusHours(2);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(8.40, tarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_MaisDeUmaHoraComDesconto() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 8, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 1, 1, 10, 0);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, true);
        assertEquals(6.7, tarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_MaisDeUmDia() { // Teste falhando, porém o valor esperado está correto
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 8, 0);
        LocalDateTime saida = LocalDateTime.of(2022, 1, 3, 9, 0);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(150.0, tarifa, 0.01);
    }

    // Teste para casos inválidos/falhas

    @Test
    public void testCalcularTarifa_MenosUmaHora() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusHours(-1);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(0.00, tarifa, 0.01);
    }

    @Test
    public void testCalcularTarifa_MaisZeroHora() {
        Estacionamento estacionamento = new Estacionamento();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusHours(0);
        double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
        assertEquals(0.00, tarifa, 0.01);
    }

    @Test
    public void testEncerrarTicket_DuranteEstacionamentoFechado() {
        try {
            Estacionamento estacionamento = new Estacionamento();
            LocalDateTime entrada = LocalDateTime.of(2022, 1, 1, 20, 0);
            LocalDateTime saida = LocalDateTime.of(2022, 1, 2, 7, 0);
            double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
            assertEquals(0.0, tarifa, 0.01);
        } catch (IllegalArgumentException e) {
            assertEquals("O estacionamento está fechado.", e.getMessage(), " falhou");
        }
    }
    

    @Test
    public void testEncerrarTicket_AntesDaEntrada() {
        try {
            Estacionamento estacionamento = new Estacionamento();
            LocalDateTime entrada = LocalDateTime.now();
            LocalDateTime saida = entrada.minusHours(1);
            double tarifa = estacionamento.calcularTarifa(entrada, saida, false);
            assertEquals(0.0, tarifa, 0.01);
        } catch (IllegalArgumentException e) {
            assertEquals("A data/hora de saída não pode ser antes da entrada.", e.getMessage(), " falhou");
        }
    }
}