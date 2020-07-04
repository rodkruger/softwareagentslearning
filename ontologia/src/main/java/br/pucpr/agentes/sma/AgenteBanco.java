package br.pucpr.agentes.sma;


import br.pucpr.agentes.ontologia.*;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.leap.LEAPCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.UUID;

public class AgenteBanco extends Agent {

    private Codec codec = new LEAPCodec();
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
                                System.out.println("Solicitando saldo ...");
                                if (!contas.containsKey(msg.getSender())) {
                                    Conta conta = new Conta();
                                    conta.setNumero(UUID.randomUUID().toString());
                                    conta.setTipo("Corrente");
                                    conta.setSaldo(0.0f);
                                    contas.put(msg.getSender(), conta);
                                }
                                Conta conta = contas.get(msg.getSender());
                                ACLMessage reply = msg.createReply();
                                reply.setContent("Saldo: " + conta.getSaldo());
                                reply.setPerformative(ACLMessage.INFORM);
                                EnviarSaldoAction enviarSaldoAction = new EnviarSaldoAction();
                                enviarSaldoAction.setConta(conta);
                                Action action2 = new Action(msg.getSender(),
                                        enviarSaldoAction);
                                getContentManager().fillContent(reply, action2);
                                send(reply);
                                //System.out.println(reply);
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
                        ContentElement content = getContentManager().extractContent(msg);
                        if (content instanceof Action) {
                            Action action = (Action) content;
                            if (action.getAction() instanceof EnviarSaldoAction) {
                                System.out.println("Enviando saldo ...");
                                EnviarSaldoAction enviar = (EnviarSaldoAction) action.getAction();
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


        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        ContentElement content = getContentManager().extractContent(msg);
                        if (content instanceof Action) {
                            Action action = (Action) content;
                            if (action.getAction() instanceof SaqueAction) {
                                if (!contas.containsKey(msg.getSender())) {
                                    Conta conta = new Conta();
                                    conta.setNumero(UUID.randomUUID().toString());
                                    conta.setTipo("Corrente");
                                    conta.setSaldo(0.0f);
                                    contas.put(msg.getSender(), conta);
                                }

                                Conta conta = contas.get(msg.getSender());

                                SaqueAction saque = (SaqueAction) action.getAction();
                                System.out.print("Conta " + conta.getNumero() + "; Saldo: " + conta.getSaldo() + "; ");
                                float valor = BigDecimal.valueOf(Math.random() * 100.0f).setScale(2, BigDecimal.ROUND_HALF_EVEN).floatValue();
                                System.out.print("Sacando " + valor + "; ");
                                float saldo = BigDecimal.valueOf(conta.getSaldo() - valor).setScale(2, BigDecimal.ROUND_HALF_EVEN).floatValue();

                                if (saldo < 0.0f) {
                                    ACLMessage reply = msg.createReply();
                                    reply.setPerformative(ACLMessage.FAILURE);
                                    EnviarSaldoAction enviarSaldoAction = new EnviarSaldoAction();
                                    enviarSaldoAction.setConta(conta);
                                    Action action2 = new Action(msg.getSender(), enviarSaldoAction);
                                    getContentManager().fillContent(reply, action2);
                                    send(reply);
                                    System.out.println("Saldo insuficiente! Operação cancelada!");
                                } else {
                                    conta.setSaldo(saldo);
                                    ACLMessage reply = msg.createReply();
                                    reply.setPerformative(ACLMessage.INFORM);
                                    EnviarSaldoAction enviarSaldoAction = new EnviarSaldoAction();
                                    enviarSaldoAction.setConta(conta);
                                    Action action2 = new Action(msg.getSender(), enviarSaldoAction);
                                    getContentManager().fillContent(reply, action2);
                                    send(reply);
                                    System.out.println("Ok! Novo saldo: " + conta.getSaldo());
                                }
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
                        ContentElement content = getContentManager().extractContent(msg);
                        if (content instanceof Action) {
                            Action action = (Action) content;
                            if (action.getAction() instanceof DepositarAction) {

                                if (!contas.containsKey(msg.getSender())) {
                                    Conta conta = new Conta();
                                    conta.setNumero(UUID.randomUUID().toString());
                                    conta.setTipo("Corrente");
                                    conta.setSaldo(0.0f);
                                    contas.put(msg.getSender(), conta);
                                }

                                Conta conta = contas.get(msg.getSender());

                                DepositarAction deposito = (DepositarAction) action.getAction();
                                System.out.print("Conta " + conta.getNumero() + "; Saldo: " + conta.getSaldo() + "; ");
                                float valor = BigDecimal.valueOf(Math.random() * 100.0f).setScale(2, BigDecimal.ROUND_HALF_EVEN).floatValue();
                                System.out.print("Depositando " + valor + "; ");
                                float saldo = BigDecimal.valueOf(conta.getSaldo() + valor).setScale(2, BigDecimal.ROUND_HALF_EVEN).floatValue();
                                conta.setSaldo(saldo);

                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.INFORM);
                                EnviarSaldoAction enviarSaldoAction = new EnviarSaldoAction();
                                enviarSaldoAction.setConta(conta);
                                Action action2 = new Action(msg.getSender(),
                                        enviarSaldoAction);
                                getContentManager().fillContent(reply, action2);
                                send(reply);

                                System.out.println("Ok! Novo saldo: " + conta.getSaldo());
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