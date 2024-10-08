package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private static LocalDateTime entrada = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Ticket(LocalDateTime entrada) {
        Ticket.entrada = entrada;
        System.out.println("Ticket emitido em: " + entrada.format(formatter));
    }

    public static void main(String[] args) {
        new Ticket(entrada);
    }

}