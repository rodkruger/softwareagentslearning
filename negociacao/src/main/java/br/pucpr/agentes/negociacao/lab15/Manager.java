package br.pucpr.agentes.negociacao.lab15;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.UUID;

public class Manager extends Agent {

    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                log("onTick.....................");
                //Get Contractor
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription servico = new ServiceDescription();
                servico.setType("contractor");
                template.addServices(servico);
                try {
                    DFAgentDescription[] agentes = DFService.search(myAgent, template);
                    for (DFAgentDescription agente : agentes) {
                        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
                        message.setConversationId(UUID.randomUUID().toString());
                        message.addReceiver(agente.getName());
                        send(message);
                    }
                } catch (FIPAException fe) {
                    fe.printStackTrace();
                }
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    switch (msg.getPerformative()) {
                        case ACLMessage.REFUSE:
                            log(msg.getConversationId() + ": >> REFUSE <<");
                            break;
                        case ACLMessage.PROPOSE:
                            log(msg.getConversationId() + ": PROPOSE");
                            send(makeProposal(msg));
                            break;
                        case ACLMessage.CANCEL:
                            log(msg.getConversationId() + ": >> CANCEL <<");
                            break;
                        case ACLMessage.INFORM:
                            log(msg.getConversationId() + ": >> INFORM <<");
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
