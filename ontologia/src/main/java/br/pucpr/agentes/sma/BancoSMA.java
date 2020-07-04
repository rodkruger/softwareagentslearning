package br.pucpr.agentes.sma;

public class BancoSMA {

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula11",
                "-local-host", "127.0.0.1",
                "Cliente01:br.pucpr.agentes.sma.AgenteCliente;Banco:br.pucpr.agentes.sma.AgenteBanco"};
        jade.Boot.main(init);
    }

}
