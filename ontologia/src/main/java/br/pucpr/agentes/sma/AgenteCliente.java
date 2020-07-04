package br.pucpr.agentes.sma;

import br.pucpr.agentes.ontologia.BancoOntology;
import br.pucpr.agentes.ontologia.SolicitarSaldoAction;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteCliente extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontology = BancoOntology.getInstance();

    @Override
    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                    //dados da mensagem
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.setLanguage(codec.getName());
                    msg.setOntology(BancoOntology.getInstance().getName());
                    //dados da ontologia Banco
                    AID agenteBanco = new AID("Banco", false);
                    SolicitarSaldoAction solicitar = new SolicitarSaldoAction();
                    Action action = new Action(agenteBanco, solicitar);
                    getContentManager().fillContent(msg, action);
                    //envio
                    msg.addReceiver(agenteBanco);
                    send(msg);
                    System.out.println(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
