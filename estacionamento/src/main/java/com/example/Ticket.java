package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private int id;
    private Automovel automovel;
    private LocalDateTime entrada; 
    private LocalDateTime saida;
    private boolean pago;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Ticket(int id, Automovel automovel, LocalDateTime entrada) {
        this.id = id;
        this.automovel = automovel;
        this.entrada = entrada;
        this.pago = false;
    }

    public int getId() {
        return id;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public boolean isPago() {
        return pago;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
        System.out.print("Ticket pago! Volte sempre");
    }

    public String formatarEntrada() {
        return this.entrada.format(formatter);
    }

    public String formatarSaida() {
        return this.saida != null ? this.saida.format(formatter) : "Sem saída registrada";
    }
}
