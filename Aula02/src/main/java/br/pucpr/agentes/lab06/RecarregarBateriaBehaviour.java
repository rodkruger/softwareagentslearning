package br.pucpr.agentes.lab06;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class RecarregarBateriaBehaviour extends TickerBehaviour {
    private Bateria bateria;
    boolean carregar;

    public RecarregarBateriaBehaviour(Agent a, Bateria bateria, long period) {
        super(a, period);
        this.bateria = bateria;
    }

    @Override
    protected void onTick() {
        if (bateria.getCarga() <= 2) {
            carregar = true;
        } else if (bateria.getCarga() >= 8) {
            carregar = false;
        }
        if (carregar) {
            System.out.println("Carregando...");
            bateria.setCarga(bateria.getCarga() + 1);
        }
    }
}
