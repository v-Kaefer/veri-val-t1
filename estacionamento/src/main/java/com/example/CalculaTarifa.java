package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculaTarifa implements Estacionamento {

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean isVip;
    private Duration duracao;
    private long minutos;

    public CalculaTarifa(Ticket ticket, Automovel automovel) {
        this.entrada = ticket.getEntrada();
        this.saida = ticket.getSaida();
        this.isVip = automovel.isVip();
        this.duracao = Duration.between(entrada, saida);
        this.minutos = duracao.toMinutes();
    }

    public double calcularTarifa () {
        // Calcular a duração total em minutos
        Duration duracao = Duration.between(entrada, saida);
        long minutos = duracao.toMinutes();
        double valor = 0.0;

        /* Isso é impossível, a menos que implementado propositalmente
        // Verificar se as datas são válidas
        if (saida.isBefore(entrada)) {
            throw new IllegalArgumentException("A data/hora de saída não pode ser antes da entrada.");
        }
        */

        // Verificar se o estacionamento está fechado
        if (saida.getHour() >= 20 || saida.getHour() < 8) {
            throw new IllegalArgumentException("O estacionamento está fechado.");
        }

        // Verificar cortesia
        if (minutos <= CORTESIA) {
            valor = 0.0;
        }

        // Até 1 hora
        if (minutos > CORTESIA && minutos <= 60) {
            valor = VALOR_FIXO;
            return isVip ? valor * 0.5 : valor;
        }
        
        // Acima de 1 hora
        else if (!isPernoite() && minutos > 60) {
            // Calcular horas adicionais
            long horasAdicionais = (minutos - 60) / 60;
            valor = VALOR_FIXO + (horasAdicionais * VALOR_POR_HORA);
            return isVip ? valor * 0.5 : valor;
        }

        // Verificar pernoite
        if (saida.getHour() >= 8 && !entrada.toLocalDate().isEqual(saida.toLocalDate())) {
            long diasPernoite = calcularDiasPernoite();
            valor = VALOR_PERNOITE * diasPernoite;
            return isVip ? valor * 0.5 : valor;
        }

        return valor;
    }

    private boolean isPernoite() {
        // Verifica se a saída é após as 08:00 da manhã do dia seguinte ou posteriores
        LocalDateTime inicioPernoite = entrada.toLocalDate().atTime(LocalTime.of(8, 0)).plusDays(1);
        return !saida.isBefore(inicioPernoite);
    }

    private long calcularDiasPernoite() {
        // Calcula o número de dias de pernoite
        LocalDateTime inicioPernoite = entrada.toLocalDate().atTime(LocalTime.of(8, 0)).plusDays(1);
        Duration duracaoPernoite = Duration.between(inicioPernoite, saida);

        long dias = duracaoPernoite.toDays();
        if (duracaoPernoite.toHoursPart() > 0 || duracaoPernoite.toMinutesPart() > 0) {
            dias++; // Conta o dia parcial como um dia inteiro
        }
        return dias + 1; // Inclui o primeiro dia de pernoite
    }
}
