package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculadoraTarifa {

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean clienteVip;

    public CalculadoraTarifa(LocalDateTime entrada, LocalDateTime saida, boolean clienteVip) {
        this.entrada = entrada;
        this.saida = saida;
        this.clienteVip = clienteVip;
    }

    public double calcular() {
        // Verificar se as datas são válidas
        if (saida.isBefore(entrada)) {
            throw new IllegalArgumentException("A data/hora de saída não pode ser antes da entrada.");
        }

        // Calcular a duração total em minutos
        Duration duracao = Duration.between(entrada, saida);
        long minutosTotais = duracao.toMinutes();

        double valor = 0.0;

        // Regra de 15 minutos de cortesia
        if (minutosTotais <= 15) {
            valor = 0.0;
        }
        // Até 1 hora (inclusive)
        else if (minutosTotais >= 60) {
            valor = 5.90;
        }
        // Acima de 1 hora e não é pernoite
        else if (!isPernoite()) {
            // Calcular horas adicionais
            long horasAdicionais = (minutosTotais - 60) / 60;
            if ((minutosTotais - 60) % 60 > 0) {
                horasAdicionais++; // Arredonda para cima se houver minutos extras
            }
            valor = 5.90 + (horasAdicionais * 2.50);
        }
        // Caso seja pernoite
        else {
            long diasPernoite = calcularDiasPernoite();
            valor = diasPernoite * 50.00;
        }

        // Aplicar desconto para cliente VIP
        if (clienteVip) {
            valor *= 0.5;
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
