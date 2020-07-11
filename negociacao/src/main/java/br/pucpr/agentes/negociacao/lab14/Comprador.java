package br.pucpr.agentes.negociacao.lab14;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.Iterator;

public class Comprador extends Agent {

    protected void setup() {

        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                //listar servicos disponiveis
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription servico = new ServiceDescription();
                servico.setType("vendas");
                template.addServices(servico);
                try {
                    DFAgentDescription[] agentes = DFService.search(myAgent, template);
                    for (DFAgentDescription agente : agentes) {
                        Iterator services = agente.getAllServices();
                        while (services.hasNext()) {
                            ServiceDescription service = (ServiceDescription) services.next();
                            System.out.println("Agente: "
                                    + agente.getName().getLocalName() + ". tipo: "
                                    + service.getType() + ". servico:" + service.getName());
                        }
                    }
                } catch (FIPAException fe) {
                    fe.printStackTrace();
                }
            }
        });

    }
}
