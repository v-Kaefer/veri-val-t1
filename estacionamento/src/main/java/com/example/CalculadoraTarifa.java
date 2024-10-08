package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculadoraTarifa {

    private static final LocalTime HORARIO_ABERTURA = LocalTime.of(8, 0);
    private static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(2, 0);

    private Ticket ticket;
    private boolean clienteVip;

    public CalculadoraTarifa(Ticket ticket) {
        this.ticket = ticket;
        this.clienteVip = ticket.getAutomovel().isVip();
    }

    public double calcular() {
        validarHorarioSaida(ticket.getSaida().toLocalTime());

        if (ticket.getSaida() == null) {
            throw new IllegalStateException("A saída ainda não foi registrada.");
        }

        if (ticket.getSaida().isBefore(ticket.getEntrada())) {
            throw new IllegalArgumentException("A data/hora de saída não pode ser antes da entrada.");
        }

        Duration duracao = Duration.between(ticket.getEntrada(), ticket.getSaida());
        long minutosTotais = duracao.toMinutes();

        double valor = 0.0;

        if (minutosTotais <= 15) {
            valor = 0.0;
        } else if (minutosTotais <= 60) {
            valor = 5.90;
        } else if (!isPernoite()) {
            long horasAdicionais = (minutosTotais - 60) / 60;
            if ((minutosTotais - 60) % 60 > 0) {
                horasAdicionais++;
            }
            valor = 5.90 + (horasAdicionais * 2.50);
        } else {
            long diasPernoite = calcularDiasPernoite();
            valor = diasPernoite * 50.00;
        }

        if (clienteVip) {
            valor *= 0.5;
        }

        return valor;
    }

    // Validação de horário de saída
    private void validarHorarioSaida(LocalTime horaSaida) {
        if (horaSaida.isBefore(HORARIO_ABERTURA) && horaSaida.isAfter(HORARIO_FECHAMENTO)) {
            throw new IllegalArgumentException("A saída não pode ocorrer fora do horário de funcionamento (08:00 até 02:00).");
        }
    }

    private boolean isPernoite() {
        LocalDateTime inicioPernoite = ticket.getEntrada().toLocalDate().atTime(LocalTime.of(8, 0)).plusDays(1);
        return !ticket.getSaida().isBefore(inicioPernoite);
    }

    private long calcularDiasPernoite() {
        LocalDateTime inicioPernoite = ticket.getEntrada().toLocalDate().atTime(LocalTime.of(8, 0)).plusDays(1);
        Duration duracaoPernoite = Duration.between(inicioPernoite, ticket.getSaida());

        long dias = duracaoPernoite.toDays();
        if (duracaoPernoite.toHours() > 0 || duracaoPernoite.toMinutes() > 0) {
            dias++;
        }
        return dias + 1;
    }
}
