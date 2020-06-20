package br.pucpr.agentes.lab08;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Alexa extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    System.out.println(message);
                    ACLMessage resposta_padrao = new ACLMessage(ACLMessage.FAILURE);
                    resposta_padrao.addReceiver(message.getSender());
                    resposta_padrao.setContent("Desculpe-me, ainda não tenho protocolo de comunicação definido!");
                    send(resposta_padrao);

                    ACLMessage resposta_padrao2 = new ACLMessage(ACLMessage.INFORM);
                    resposta_padrao2.addReceiver(message.getSender());
                    resposta_padrao2.setProtocol("fipa-contract-net");
                    resposta_padrao2.setContent("Futuramente entenderei o FIPE Contract-NET!");
                    send(resposta_padrao2);
                } else {
                    block();
                }
            }
        });
    }

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula03",
                "-local-host", "127.0.0.1",
                "Alexa:br.pucpr.agentes.lab08.Alexa"};
        jade.Boot.main(init);
    }
}
