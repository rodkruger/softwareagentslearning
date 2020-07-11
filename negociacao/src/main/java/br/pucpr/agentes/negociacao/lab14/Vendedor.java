package br.pucpr.agentes.negociacao.lab14;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class Vendedor extends Agent {

    protected void setup() {

        String tipoServico = getArguments()[0].toString();
        String nomeServico = getArguments()[1].toString();

        ServiceDescription servico = new ServiceDescription();
        servico.setType(tipoServico);
        servico.setName(nomeServico);

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(servico);

        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

}
