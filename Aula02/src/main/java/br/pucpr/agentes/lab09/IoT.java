package br.pucpr.agentes.lab09;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class IoT extends Agent {

    private HashMap<String, String> coisas = new HashMap<String,
            String>();

    @Override
    protected void setup() {
        String contato = getArguments()[0].toString();

        addBehaviour(new TickerBehaviour(this, 1000) {
            @Override
            protected void onTick() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID(contato, false));
                long agora = GregorianCalendar.getInstance().getTimeInMillis();
                coisas.put(getLocalName(), getLocalName() + ",OK," + agora);
                msg.setContent(toStr(coisas));
                send(msg);
            }
        });

        addBehaviour(new TickerBehaviour(this, 10000) {
            @Override
            protected void onTick() {
                log(toStr(coisas));
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getPerformative() == ACLMessage.INFORM) {
                        // Guardar as informações recebidad
                        String[] maisCoisas = msg.getContent().split(";");
                        for (String coisa : maisCoisas) {
                            if (coisa.length() < 2) return;
                            String[] campos = coisa.split(",");
                            String aid = campos[0];
                            String horaCa = campos[2];
                            if (!coisas.containsKey(aid)) {
                                coisas.put(aid, coisa);
                            } else {
                                String horaCb = coisas.get(aid).split(",")[2];
                                if (Long.parseLong(horaCa) > Long.parseLong(horaCb)) {
                                    coisas.put(aid, coisa);
                                }
                            }
                        }
                    }
                } else {
                    block();
                }
            }
        });

        addBehaviour(new TickerBehaviour(this, 5000) {
            @Override
            protected void onTick() {
                Iterator<String> it = coisas.keySet().iterator();
                while (it.hasNext()) {
                    String coisa = coisas.get(it.next());
                    String aid = coisa.split(",")[0];
                    String hora = coisa.split(",")[2];
                    long agora = GregorianCalendar.getInstance().getTimeInMillis();
                    if (agora > Long.parseLong(hora) + 10000) {
                        coisas.put(aid, aid + ",NOK," + agora);
                    }
                }
            }
        });
    }

    private String toStr(HashMap<String, String> coisas) {
        if (coisas.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Iterator<String> it = coisas.keySet().iterator();

        while (it.hasNext()) {
            sb.append(";").append(coisas.get(it.next()));
        }

        return sb.toString().substring(1);
    }

    public void log(String msg) {
        System.out.println(getLocalName() + ": " + msg);
    }

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula03",
                "-local-host", "127.0.0.1",
                "Geladeira:br.pucpr.agentes.lab09.IoT(TV);TV:br.pucpr.agentes.lab09.IoT(Celular);Celular:br.pucpr.agentes.lab09.IoT(Fogao);Fogao:br.pucpr.agentes.lab09.IoT(Geladeira)"};
        jade.Boot.main(init);
    }
}
