package br.pucpr.agentes.lab18;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;

import java.util.UUID;

public class Clone extends Agent {

    private int vida = 30;

    @Override
    protected void setup() {
        //replicação
        addBehaviour(new TickerBehaviour(this, 5000) {
            @Override
            protected void onTick() {
                doClone(new ContainerID("Main-Container", null), "clone_" + UUID.randomUUID());
            }
        });
        //morte
        addBehaviour(new TickerBehaviour(this, 1000) {
            @Override
            protected void onTick() {
                System.out.println(getLocalName() + ": vida=" + vida);
                if (vida-- < 1) {
                    myAgent.doDelete();
                }
            }
        });
    }

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula06",
                "-local-host", "127.0.0.1",
                "dolly:br.pucpr.agentes.lab18.Clone"};
        jade.Boot.main(init);
    }
    
}
