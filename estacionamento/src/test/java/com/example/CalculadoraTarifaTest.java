package com.example;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CalculadoraTarifaTest {

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "..src/test_cases.csv", numLinesToSkip = 0)
    void testCalculaValor(String casoTeste, boolean clienteVip, String entradaStr, String saidaStr, double valorEsperado) {
        LocalDateTime entrada = LocalDateTime.parse(entradaStr.replace("'", ""));
        LocalDateTime saida = LocalDateTime.parse(saidaStr.replace("'", ""));

        CalculadoraTarifa calculadora = new CalculadoraTarifa(entrada, saida, clienteVip);
        double valorCalculado = calculadora.calcular();

        assertEquals(valorEsperado, valorCalculado, 0.01, casoTeste + " falhou");
    }

    // Teste para casos inválidos
    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "..src/test_cases.csv", numLinesToSkip = 0)
    void testEntradaSaidaInvalidas(String casoTeste, boolean clienteVip, String entradaStr, String saidaStr) {
        LocalDateTime entrada = LocalDateTime.parse(entradaStr.replace("'", ""));
        LocalDateTime saida = LocalDateTime.parse(saidaStr.replace("'", ""));

        CalculadoraTarifa calculadora = new CalculadoraTarifa(entrada, saida, clienteVip);

        try {
            calculadora.calcular();
        } catch (IllegalArgumentException e) {
            assertEquals("A data/hora de saída não pode ser antes da entrada.", e.getMessage(), casoTeste + " falhou");
        }
    }
}
