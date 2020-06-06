package br.pucpr.agentes.lab04;

import jade.core.Agent;

public class Jarvis extends Agent {

    private void log(String msg) {
        System.out.println(msg);
    }

    @Override
    protected void setup() {
        log("Olá, eu sou o agente " + getName());
    }

}
