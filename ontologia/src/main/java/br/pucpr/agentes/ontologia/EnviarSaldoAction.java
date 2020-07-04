package br.pucpr.agentes.ontologia;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: EnviarSaldoAction
* @author ontology bean generator
* @version 2020/07/4, 11:36:27
*/
public class EnviarSaldoAction implements AgentAction {

   /**
* Protege name: conta
   */
   private Conta conta;
   public void setConta(Conta value) { 
    this.conta=value;
   }
   public Conta getConta() {
     return this.conta;
   }

}
