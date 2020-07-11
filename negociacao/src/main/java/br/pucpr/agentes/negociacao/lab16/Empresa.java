package br.pucpr.agentes.negociacao.lab16;

import br.pucpr.agentes.negociacao.lab15.Contractor;
import jade.lang.acl.ACLMessage;

import java.util.Hashtable;

public class Empresa extends Contractor {

    private Hashtable<String, Double> ofertas;
    private Double propostaInicial;
    private Double propostaLimite;
    private Double grau;

    protected void setup() {
        super.setup();
        ofertas = new Hashtable<String, Double>();
        propostaInicial = Double.parseDouble(getArguments()[0].toString());
        propostaLimite = Double.parseDouble(getArguments()[1].toString());
        grau = Double.parseDouble(getArguments()[2].toString());
    }

    protected ACLMessage makeProposal(ACLMessage msg) {
        ACLMessage reply = msg.createReply();
        switch (msg.getPerformative()) {
            case ACLMessage.REQUEST:
                if (!ofertas.containsKey(msg.getConversationId())) {
                    ofertas.put(msg.getConversationId(), propostaInicial);
                }
                reply.setContent(propostaInicial.toString());
                reply.setPerformative(ACLMessage.PROPOSE);
                break;
            case ACLMessage.PROPOSE:
                Double propostaRecebida = Double.parseDouble(msg.getContent());
                if (propostaRecebida >= propostaLimite) {
                    Double minhaPropostaAtual = ofertas.get(msg.getConversationId());
                    Double novaProposta = minhaPropostaAtual - (minhaPropostaAtual -
                            propostaRecebida) * grau;
                    if (novaProposta >= propostaLimite) {
                        reply.setPerformative(ACLMessage.PROPOSE);
                        reply.setContent(novaProposta.toString());
                        ofertas.put(msg.getConversationId(), novaProposta);
                    } else {
                        reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                    }
                } else {
                    reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                }
                break;
        }
        log(reply.toString());
        return reply;
    }
}
