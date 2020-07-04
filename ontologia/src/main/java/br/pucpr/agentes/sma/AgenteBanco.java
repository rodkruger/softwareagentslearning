package br.pucpr.agentes.sma;

import br.pucpr.agentes.ontologia.BancoOntology;
import br.pucpr.agentes.ontologia.Conta;
import br.pucpr.agentes.ontologia.EnviarSaldoAction;
import br.pucpr.agentes.ontologia.SolicitarSaldoAction;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Hashtable;
import java.util.UUID;

public class AgenteBanco extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BancoOntology.getInstance();

    private Hashtable<AID, Conta> contas = new Hashtable<AID, Conta>();

    @Override
    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        ContentElement content = getContentManager().extractContent(msg);
                        if (content instanceof Action) {
                            Action action = (Action) content;
                            if (action.getAction() instanceof SolicitarSaldoAction) {
                                if (!contas.containsKey(msg.getSender())) {
                                    Conta conta = new Conta();
                                    conta.setNumero(UUID.randomUUID().toString());
                                    conta.setTipo("Corrente");
                                    conta.setSaldo(0.0f);
                                    contas.put(msg.getSender(), conta);
                                }
                                Conta conta = contas.get(msg.getSender());
                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.INFORM);
                                EnviarSaldoAction enviarSaldoAction = new EnviarSaldoAction();
                                enviarSaldoAction.setConta(conta);
                                Action action2 = new Action(msg.getSender(),
                                        enviarSaldoAction);
                                getContentManager().fillContent(reply, action2);
                                send(reply);
                                System.out.println(reply);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        ContentElement content =
                                getContentManager().extractContent(msg);
                        if (content instanceof Action) {
                            Action action = (Action) content;
                            if (action.getAction() instanceof
                                    EnviarSaldoAction) {
                                EnviarSaldoAction enviar = (EnviarSaldoAction)
                                        ((Action) content).getAction();
                                Conta conta = enviar.getConta();
                                System.out.println("Conta: " +
                                        conta.getNumero() + "\n Tipo: " +
                                        conta.getTipo() + "\n Saldo: " +
                                        conta.getSaldo());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }
}
