package br.pucpr.agentes.lab19;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.ControllerException;

public class Movel extends Agent {

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new TickerBehaviour(this, 5000) {
            @Override
            protected void onTick() {
                try {
                    String meuContainer = getContainerController().getContainerName();
                    switch (meuContainer) {
                        case "America":
                            doMove(new ContainerID("Europa", null));
                            break;
                        case "Europa":
                            doMove(new ContainerID("Asia", null));
                            break;
                        case "Asia":
                            doMove(new ContainerID("America", null));
                            break;
                    }
                } catch (ControllerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        String[] args1 = {"-gui", "-name", "lab19", "-local-host", "127.0.0.1", "-containername", "Plataforma"};
        jade.Boot.main(args1);
        String[] args2 = {"-gui", "-container", "-host", "127.0.0.1", "-container-name",
                "America",
                "America:br.pucpr.agentes.lab19.Movel"};
        jade.Boot.main(args2);
        String[] args3 = {"-gui", "-container", "-host", "127.0.0.1", "-container-name",
                "Europa",
                "Europa:br.pucpr.agentes.lab19.Movel"};
        jade.Boot.main(args3);
        String[] args4 = {"-gui", "-container", "-host", "127.0.0.1", "-container-name",
                "Asia",
                "Asia:br.pucpr.agentes.lab19.Movel"};
        jade.Boot.main(args4);
    }

}
