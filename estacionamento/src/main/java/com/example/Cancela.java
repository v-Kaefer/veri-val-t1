package com.example;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cancela implements Estacionamento {
    

    // Método para emitir o ticket na entrada, vinculando ao automóvel
    public Ticket emitirTicket(Automovel automovel) {
        LocalDateTime entrada = LocalDateTime.now();
        validarHorarioFuncionamento(entrada.toLocalTime());
          // Passa o horário de entrada
        Ticket ticket = new Ticket(gerarNovoId(), automovel, entrada);
        return ticket;
    }

    // Método para validar se o horário está dentro do funcionamento do estacionamento
    private void validarHorarioFuncionamento(LocalTime entrada) {
        if (entrada.isBefore(HORARIO_ABERTURA) && entrada.isAfter(HORARIO_FECHAMENTO)) {
            throw new IllegalArgumentException("O estacionamento está fechado. Horário de funcionamento: 08:00 até 02:00.");
        }
    }

    // Gera novo ID para os tickets (implementação simplificada)
    private int gerarNovoId() {
        return (int) (Math.random() * 100);
    }

    public double processarSaida(Ticket ticket, Automovel automovel) {
        LocalDateTime saida = LocalDateTime.now();
        ticket.setSaida(saida);
        double tarifa = App.calcularTarifa(ticket, ticket.getAutomovel());
        if (!ticket.isPago()) { // Simplificação para testes
            automovel.realizarPagamento();
        }
        return tarifa;
    }
}