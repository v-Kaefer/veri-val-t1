package com.example;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cancela {
    
    private static final LocalTime HORARIO_ABERTURA = LocalTime.of(8, 0);
    private static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(2, 0);

    // Método para emitir o ticket na entrada, vinculando ao automóvel
    public Ticket emitirTicket(Automovel automovel) {
        LocalDateTime agora = LocalDateTime.now();
        validarHorarioFuncionamento(agora.toLocalTime());

        Ticket ticket = new Ticket(gerarNovoId(), automovel, agora);  // Passa o horário de entrada
        return ticket;
    }

    // Método para validar se o horário está dentro do funcionamento do estacionamento
    private void validarHorarioFuncionamento(LocalTime horaAtual) {
        if (horaAtual.isBefore(HORARIO_ABERTURA) || horaAtual.isAfter(HORARIO_FECHAMENTO)) {
            throw new IllegalArgumentException("O estacionamento está fechado. Horário de funcionamento: 08:00 até 02:00.");
        }
    }

    // Geração de novo ID para os tickets (implementação simplificada)
    private int gerarNovoId() {
        return (int) (Math.random() * 10000); // Apenas um exemplo de geração de ID
    }

    // Método para processar a saída do automóvel e calcular a tarifa
    public double processarSaida(Ticket ticket) {
        if (ticket.isPago()) {
            throw new IllegalStateException("O ticket já foi pago.");
        }

        LocalDateTime saida = LocalDateTime.now();
        ticket.setSaida(saida);

        CalculadoraTarifa calculadora = new CalculadoraTarifa(ticket);
        double valorDevido = calculadora.calcular();

        ticket.setPago(true);
        return valorDevido;
    }
}
