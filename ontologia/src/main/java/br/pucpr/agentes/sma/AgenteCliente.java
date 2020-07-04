package br.pucpr.agentes.sma;


import br.pucpr.agentes.ontologia.BancoOntology;
import br.pucpr.agentes.ontologia.DepositarAction;
import br.pucpr.agentes.ontologia.SaqueAction;
import br.pucpr.agentes.ontologia.SolicitarSaldoAction;
import jade.content.lang.Codec;
import jade.content.lang.leap.LEAPCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteCliente extends Agent {

    private Codec codec = new LEAPCodec();
    private Ontology ontology = BancoOntology.getInstance();

    @Override
    protected void setup() {
        AID agenteBanco = new AID("Banco", false);

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
                    SolicitarSaldoAction solicitar = new SolicitarSaldoAction();
                    Action action = new Action(agenteBanco, solicitar);
                    getContentManager().fillContent(msg, action);
                    //envio
                    msg.addReceiver(agenteBanco);
                    send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addBehaviour(new TickerBehaviour(this, 500) {
            @Override
            public void onTick() {
                try {
                    //dados da mensagem
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.setLanguage(codec.getName());
                    msg.setOntology(BancoOntology.getInstance().getName());
                    //dados da ontologia Banco
                    DepositarAction deposito = new DepositarAction();
                    Action action = new Action(agenteBanco, deposito);
                    getContentManager().fillContent(msg, action);
                    //envio
                    msg.addReceiver(agenteBanco);
                    send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addBehaviour(new TickerBehaviour(this, 500) {
            @Override
            public void onTick() {
                try {
                    //dados da mensagem
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.setLanguage(codec.getName());
                    msg.setOntology(BancoOntology.getInstance().getName());
                    //dados da ontologia Banco
                    SaqueAction saque = new SaqueAction();
                    Action action = new Action(agenteBanco, saque);
                    getContentManager().fillContent(msg, action);
                    //envio
                    msg.addReceiver(agenteBanco);
                    send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
