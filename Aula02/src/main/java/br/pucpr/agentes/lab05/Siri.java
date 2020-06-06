package br.pucpr.agentes.lab05;

import jade.core.Agent;

public class Siri extends Agent {

    private Integer hora;
    private String diaSemana;

    private void log(String msg) {
        System.out.println(msg);
    }

    private void saySalute() {
        if (this.hora > 18) {
            log("Boa noite!");
        } else if (this.hora > 12) {
            log("Boa tarde!");
        } else {
            log("Bom dia!");
        }
    }

    @Override
    protected void setup() {

        Object[] args = getArguments();
        this.hora = Integer.valueOf((args[0].toString()));
        this.diaSemana = args[1].toString();

        saySalute();
        log("Hoje é " + this.diaSemana);
    }

    @Override
    public void takeDown() {
        log("Até logo! Tchau!");
        saySalute();
    }

}
