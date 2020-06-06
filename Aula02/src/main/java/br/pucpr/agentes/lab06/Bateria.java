package br.pucpr.agentes.lab06;

public class Bateria {
    private int carga;

    public Bateria(int cargaInicial) {
        setCarga(cargaInicial);
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
}