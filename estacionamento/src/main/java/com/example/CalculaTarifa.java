package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculaTarifa implements Estacionamento {

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean isVip;
    private Duration horasEstacionado;

    public CalculaTarifa(Ticket ticket, Automovel automovel) {
        this.entrada = ticket.getEntrada();
        this.saida = ticket.getSaida();
        this.isVip = automovel.isVip();
        this.horasEstacionado = Duration.between(entrada, saida);
    }

    public double calcularTarifa () {
        LocalTime horarioFechamento = HORARIO_ABERTURA.plus(HORARIO_OPERACAO);
        LocalTime horarioSaida = saida.toLocalTime();

        // Calcular a duração total em minutos
        Duration duracaoEstacionado = Duration.between(entrada, saida);
        long horasEstacionado = duracaoEstacionado.toHours();
        long minutosEstacionado = duracaoEstacionado.toMinutes();
        double valor = 0.00;

        
        // Verificar cortesia
        if (minutosEstacionado <= CORTESIA) {
            return valor = 0.00;
        }

        // Até 1 hora
        if (minutosEstacionado > CORTESIA && minutosEstacionado <= 60) {
            valor = VALOR_FIXO;
            return isVip ? valor * 0.5 : valor;
        }
        
        // Acima de 1 hora
        else if (!isPernoite() && horasEstacionado > 1) {
            // Calcular horas adicionais
            long horasAdicionais = horasEstacionado - 1;
            valor = VALOR_FIXO + (horasAdicionais * VALOR_POR_HORA);
            return isVip ? valor * 0.5 : valor;
        }

        // Verificar pernoite
        if (saida.getHour() > HORARIO_FECHAMENTO.getHour() && !entrada.toLocalDate().isEqual(saida.toLocalDate())) {
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
