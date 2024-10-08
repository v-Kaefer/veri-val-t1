package com.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Estacionamento {
    private static final double VALOR_FIXO = 5.90;
    private static final double VALOR_POR_HORA = 2.50;
    private static final double VALOR_PERNOITE = 50.00;
    private static final int CORTESIA_MINUTOS = 15;
    
    public double calcularTarifa(LocalDateTime entrada, LocalDateTime saida, boolean isVip) {
        Duration duracao = Duration.between(entrada, saida);
        long minutos = duracao.toMinutes();
        
        // Verificar cortesia
        if (minutos <= CORTESIA_MINUTOS) {
            return 0.0;
        }

        // Verificar pernoite
        if (saida.getHour() >= 8 && !entrada.toLocalDate().isEqual(saida.toLocalDate())) {
            long diasPernoite = CalculadoraTarifa.calcularDiasPernoite();
            double valor = VALOR_PERNOITE * diasPernoite;
            return isVip ? valor * 0.5 : valor;
        }

        // Até 1 hora de permanência
        if (minutos > 15 && minutos <= 60) {
            double valor = VALOR_FIXO;
            return isVip ? valor * 0.5 : valor;
        }

        // Acima de 1 hora
        long horas = (minutos - 60) / 60;
        double valor = VALOR_FIXO + (horas * VALOR_POR_HORA);
        return isVip ? valor * 0.5 : valor;
    }
}
