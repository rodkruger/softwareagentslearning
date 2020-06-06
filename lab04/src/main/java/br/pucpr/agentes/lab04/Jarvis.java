package br.pucpr.agentes.lab04;

import jade.core.Agent;

public class Jarvis extends Agent {

    private void log(String msg) {
        System.out.println(msg);
    }

    @Override
    protected void setup() {
        try {
            log("Olá, eu sou o agente " + getName());
            log("Pode me chamar apenas de " + getLocalName());
            log("HAP: " + getHap());
            log("AgentState: " + getAgentState());
            log("AID: " + getAID());
            log("AMS: " + getAMS());
            log("ContainerController.name: " + getContainerController().getName());
            log("ContainerController.containerName: " + getContainerController().getContainerName());
            log("ContainerController.platformName: " + getContainerController().getPlatformName());
            log("ContentManager: " + getContentManager());
            log("O2AObject " + getO2AObject());
            log("QueueSize: " + getQueueSize());
            log("State: " + getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
