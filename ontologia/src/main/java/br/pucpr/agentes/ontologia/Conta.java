package br.pucpr.agentes.ontologia;


import jade.content.*;
import jade.util.leap.*;
import jade.core.*;

/**
* Protege name: Conta
* @author ontology bean generator
* @version 2020/07/4, 11:32:46
*/
public class Conta implements Concept {

   /**
* Protege name: saldo
   */
   private float saldo;
   public void setSaldo(float value) { 
    this.saldo=value;
   }
   public float getSaldo() {
     return this.saldo;
   }

   /**
* Protege name: tipo
   */
   private String tipo;
   public void setTipo(String value) { 
    this.tipo=value;
   }
   public String getTipo() {
     return this.tipo;
   }

   /**
* Protege name: numero
   */
   private String numero;
   public void setNumero(String value) { 
    this.numero=value;
   }
   public String getNumero() {
     return this.numero;
   }

}
