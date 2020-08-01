package br.pucpr.agentes.lab17;

import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.CreateAgent;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class Virus extends Agent {

    private static int ticks = 0;
    private int vida = 30;

    protected void setup() {
        vida = vida - ticks;
        getContentManager().registerLanguage(new SLCodec());
        getContentManager().registerOntology(JADEManagementOntology.getInstance());

        // replicação
        addBehaviour(new TickerBehaviour(this, 5000) {
            @Override
            protected void onTick() {
                ticks++;
                CreateAgent ca = new CreateAgent();
                ca.setAgentName("v" + ticks + "_" + getLocalName());
                ca.setClassName("br.pucpr.agentes.lab17.Virus");
                ca.setContainer(new ContainerID("Main-Container", null));
                //mensagem ao AMS
                try {
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    request.addReceiver(getAMS());
                    request.setOntology(JADEManagementOntology.getInstance().getName());
                    request.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
                    request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                    getContentManager().fillContent(request, new Action(getAMS(), ca));
                    addBehaviour(new AchieveREInitiator(this.myAgent, request) {
                        protected void handleFailure(ACLMessage failure) {
                            System.out.println("Falha ao criar um agente:" + failure);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //morte
        addBehaviour(new TickerBehaviour(this, 1000) {
            @Override
            protected void onTick() {
                System.out.println(getLocalName() + ": vida=" + vida);
                if (vida-- < 1) {
                    myAgent.doDelete();
                }
            }
        });
    }

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula06",
                "-local-host", "127.0.0.1",
                "v0:br.pucpr.agentes.lab17.Virus"};
        jade.Boot.main(init);
    }

}
