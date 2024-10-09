package com.example;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EstacionamentoTest {

    @Test
    public void testValorFixo() {
        assertEquals(5.90, Estacionamento.VALOR_FIXO, 0.01);
    }

    @Test
    public void testValorPorHora() {
        assertEquals(2.50, Estacionamento.VALOR_POR_HORA, 0.01);
    }

    @Test
    public void testValorPernoite() {
        assertEquals(50.00, Estacionamento.VALOR_PERNOITE, 0.01);
    }

    @Test
    public void testCortesia() {
        assertEquals(15, Estacionamento.CORTESIA);
    }

    @Test
    public void testHorarioAbertura() {
        assertEquals(LocalTime.of(8, 0), Estacionamento.HORARIO_ABERTURA);
    }

    @Test
    public void testHorarioFechamento() {
        assertEquals(LocalTime.of(2, 0), Estacionamento.HORARIO_FECHAMENTO);
    }

    @Test
    public void testHorarioOperacao() {
        assertEquals(Duration.ofHours(18), Estacionamento.HORARIO_OPERACAO);
    }
}