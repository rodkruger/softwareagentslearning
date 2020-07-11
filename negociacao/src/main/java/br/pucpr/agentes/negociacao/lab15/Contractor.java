package br.pucpr.agentes.negociacao.lab15;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Contractor extends Agent {

    protected void setup() {

        super.setup();
        ServiceDescription servico = new ServiceDescription();
        servico.setType("contractor");
        servico.setName("seller");
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(servico);

        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    //ACLMessage reply = msg.createReply();
                    switch (msg.getPerformative()) {
                        case ACLMessage.PROPOSE:
                            log(msg.getConversationId() + ": PROPOSE");
                            //rejeitar ou fazer proposta
                            send(makeProposal(msg));
                            break;
                        case ACLMessage.REQUEST:
                            log(msg.getConversationId() + ": REQUEST");
                            //rejeitar ou fazer proposta
                            send(makeProposal(msg));
                            break;
                        case ACLMessage.REJECT_PROPOSAL:
                            log(msg.getConversationId() + ": >> REJECT_PROPOSAL <<");
                            break;
                        case ACLMessage.ACCEPT_PROPOSAL:
                            log(msg.getConversationId() + ": ACCEPT_PROPOSAL");
                            ACLMessage reply = msg.createReply();
                            reply.setPerformative(Math.random() <= 0.8 ? ACLMessage.INFORM :
                                    ACLMessage.CANCEL);
                            send(reply);
                            break;
                    }
                } else {
                    block();
                }
            }
        });

    }

    protected ACLMessage makeProposal(ACLMessage msg) {
        ACLMessage reply = msg.createReply();
        switch (msg.getPerformative()) {
            case ACLMessage.PROPOSE:
            case ACLMessage.REQUEST:
                reply.setPerformative(Math.random() <= 0.7 ? ACLMessage.PROPOSE :
                        ACLMessage.REFUSE);
        }
        return reply;
    }

    protected void log(String msg) {
        System.out.println(getAID().getLocalName() + ": " + msg);
    }

}
