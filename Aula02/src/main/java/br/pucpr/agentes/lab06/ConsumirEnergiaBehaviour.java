package br.pucpr.agentes.lab06;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class ConsumirEnergiaBehaviour extends TickerBehaviour {
    protected Bateria bateria;

    public ConsumirEnergiaBehaviour(Agent a, Bateria bateria, long period) {
        super(a, period);
        this.bateria = bateria;
    }

    protected void onTick() {
        System.out.println("Bateria " + bateria.getCarga() + "%");
        if (bateria.getCarga() < 2) {
            System.out.println("Situação Crítica. Hibernando...");
            getAgent().doSuspend();
        } else {
            bateria.setCarga(bateria.getCarga() - 1);
        }
    }
}
