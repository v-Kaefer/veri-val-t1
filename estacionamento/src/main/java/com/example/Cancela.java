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
    public void validarHorarioFuncionamento(LocalTime entrada) {
        if (entrada.isBefore(HORARIO_ABERTURA) && entrada.isAfter(HORARIO_FECHAMENTO)) {
            throw new IllegalArgumentException("O estacionamento está fechado. Horário de funcionamento: 08:00 até 02:00.");
        }
    }

    // Gera novo ID para os tickets (implementação simplificada)
    // Utilizado como público para testes. Padrão seria privado.
    public int gerarNovoId() {
        return (int) (Math.random() * 1000);
    }

    public double processarSaida(Ticket ticket) {
        LocalDateTime saida = LocalDateTime.now().plusHours(1);// +1 p/ testes
        ticket.setSaida(saida);
        double tarifa = App.calcularTarifa(ticket, ticket.getAutomovel());
        if (!ticket.isPago()) { // Simplificação para testes
            ticket.getAutomovel().realizarPagamento();
        }
        return tarifa;
    }
}
