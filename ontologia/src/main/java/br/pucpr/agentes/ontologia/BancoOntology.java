// file: BancoOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package br.pucpr.agentes.ontologia;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: BancoOntology.java
 * @author ontology bean generator
 * @version 2020/07/4, 11:32:46
 */
public class BancoOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "Banco";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new BancoOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String SOLICITARSALDOACTION="SolicitarSaldoAction";
    public static final String ENVIARSALDOACTION_CONTA="conta";
    public static final String ENVIARSALDOACTION="EnviarSaldoAction";
    public static final String DEPOSITARACTION_VALOR="valor";
    public static final String DEPOSITARACTION="DepositarAction";
    public static final String SAQUEACTION_CONTA="conta";
    public static final String SAQUEACTION="SaqueAction";
    public static final String CONTA_NUMERO="numero";
    public static final String CONTA_TIPO="tipo";
    public static final String CONTA_SALDO="saldo";
    public static final String CONTA="Conta";

  /**
   * Constructor
  */
  private BancoOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema contaSchema = new ConceptSchema(CONTA);
    add(contaSchema, br.pucpr.agentes.ontologia.Conta.class);

    // adding AgentAction(s)
    AgentActionSchema saqueActionSchema = new AgentActionSchema(SAQUEACTION);
    add(saqueActionSchema, br.pucpr.agentes.ontologia.SaqueAction.class);
    AgentActionSchema depositarActionSchema = new AgentActionSchema(DEPOSITARACTION);
    add(depositarActionSchema, br.pucpr.agentes.ontologia.DepositarAction.class);
    AgentActionSchema enviarSaldoActionSchema = new AgentActionSchema(ENVIARSALDOACTION);
    add(enviarSaldoActionSchema, br.pucpr.agentes.ontologia.EnviarSaldoAction.class);
    AgentActionSchema solicitarSaldoActionSchema = new AgentActionSchema(SOLICITARSALDOACTION);
    add(solicitarSaldoActionSchema, br.pucpr.agentes.ontologia.SolicitarSaldoAction.class);

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    contaSchema.add(CONTA_SALDO, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    contaSchema.add(CONTA_TIPO, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    contaSchema.add(CONTA_NUMERO, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    saqueActionSchema.add(SAQUEACTION_CONTA, contaSchema, ObjectSchema.OPTIONAL);
    depositarActionSchema.add(DEPOSITARACTION_VALOR, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    enviarSaldoActionSchema.add(ENVIARSALDOACTION_CONTA, contaSchema, ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
