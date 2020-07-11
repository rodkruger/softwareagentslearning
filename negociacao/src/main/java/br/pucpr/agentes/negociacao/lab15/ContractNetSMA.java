package br.pucpr.agentes.negociacao.lab15;

public class ContractNetSMA {

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula11",
                "-local-host", "127.0.0.1",
                "Manager:br.pucpr.agentes.negociacao.lab15.Manager;" +
                        "Contractor01:br.pucpr.agentes.negociacao.lab15.Contractor;" +
                        "Contractor02:br.pucpr.agentes.negociacao.lab15.Contractor;"
        };

        jade.Boot.main(init);
    }

}