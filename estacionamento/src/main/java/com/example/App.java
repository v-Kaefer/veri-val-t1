package com.example;

public class App {
    public static void main(String[] args) {
        // Create an instance of the Cancela class to manage ticket operations
        Cancela cancela = new Cancela();

        // Create an example automovel (car) and emit a ticket for it
        Automovel automovel = new Automovel("ABC1234", false); // Example car with plate "ABC1234" and not VIP
        Ticket ticket = cancela.emitirTicket(automovel);

        // Display ticket information
        System.out.println("Ticket ID: " + ticket.getId());
        System.out.println("Placa do Automóvel: " + automovel.getPlaca());
        System.out.println("Entrada: " + ticket.formatarEntrada());

        // Process the exit of the automovel and calculate the tariff
        double valorDevido = cancela.processarSaida(ticket);

        // Display the calculated tariff
        System.out.println("Tarifa: " + valorDevido);
    }

    public static double calcularTarifa(Ticket ticket) {
        CalculadoraTarifa calculadora = new CalculadoraTarifa(ticket);
        return calculadora.calcular();
    }
}