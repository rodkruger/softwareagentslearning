package br.pucpr.agentes.ontologia;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: SaqueAction
* @author ontology bean generator
* @version 2020/07/4, 11:16:53
*/
public class SaqueAction implements AgentAction {

   /**
* Protege name: valor
   */
   private float valor;
   public void setValor(float value) { 
    this.valor=value;
   }
   public float getValor() {
     return this.valor;
   }

}
