package br.pucpr.agentes.lab07;

import br.pucpr.agentes.lab06.Bateria;
import br.pucpr.agentes.lab06.ConsumirEnergiaBehaviour;
import jade.core.Agent;

public class PensarBehaviour extends ConsumirEnergiaBehaviour {

    public PensarBehaviour(Agent a, Bateria bateria, long period) {
        super(a, bateria, period);
    }

    protected void onTick() {
        super.onTick();
        System.out.println(getAgent().getLocalName() + " pensando ...");
        bateria.setCarga(bateria.getCarga() - 15);
    }

}
