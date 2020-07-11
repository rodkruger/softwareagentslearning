package br.pucpr.agentes.negociacao.lab16;

import br.pucpr.agentes.negociacao.lab15.Manager;
import jade.lang.acl.ACLMessage;

import java.util.Hashtable;

public class Governo extends Manager {

    private Hashtable<String, Double> ofertas;
    private Double propostaInicial;
    private Double propostaLimite;

    protected void setup() {
        super.setup();
        ofertas = new Hashtable<String, Double>();
        propostaInicial = Double.parseDouble(getArguments()[0].toString());
        propostaLimite = Double.parseDouble(getArguments()[1].toString());
    }

    protected ACLMessage makeProposal(ACLMessage msg) {
        ACLMessage reply = msg.createReply();
        switch (msg.getPerformative()) {
            case ACLMessage.PROPOSE:
                if (!ofertas.containsKey(msg.getConversationId())) {
                    ofertas.put(msg.getConversationId(), propostaInicial);
                }
                if (msg.getContent() == null) {
                    log("Erro");
                }
                Double propostaRecebida = Double.parseDouble(msg.getContent());
                if (propostaRecebida <= propostaLimite) {
                    reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                } else {
                    Double minhaPropostaAtual = ofertas.get(msg.getConversationId());
                    Double novaProposta = minhaPropostaAtual + (propostaRecebida -
                            minhaPropostaAtual) * 0.10;
                    if (novaProposta <= propostaLimite) {
                        reply.setPerformative(ACLMessage.PROPOSE);
                        reply.setContent(novaProposta.toString());
                        ofertas.put(msg.getConversationId(), novaProposta);
                    } else {
                        reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                    }
                }
                break;
        }
        log(reply.toString());
        return reply;
    }
}
