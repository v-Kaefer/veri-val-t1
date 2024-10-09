package com.example;

public class Automovel {
    private String placa;
    private boolean vip;
    private Ticket ticket; 

    public Automovel(String placa, boolean vip) {
        this.placa = placa;
        this.vip = vip;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isVip() {
        return vip;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean realizarPagamento() {
        System.out.print("Realizando pagamento...");
        ticket.setPago(true);
        System.out.print("Pagamento realizado com sucesso!");	
        return ticket.isPago();
    }

}
