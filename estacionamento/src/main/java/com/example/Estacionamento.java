package com.example;

public interface Estacionamento {
    
    double CORTESIA_MINUTOS = 15;
    double VALOR_HORA = 5.90;
    double VALOR_HORA_ADICIONAL = 2.50;
    double VALOR_PERNOITE = 50.00;
    double DESCONTO_VIP = 0.5;

    double calcular(Ticket ticket);

    boolean isPernoite(Ticket ticket);

    long calcularDiasPernoite(Ticket ticket);
}
