package br.pucpr.agentes.negociacao.lab16;

public class NegociacaoSMA {

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aulaNegociacao",
                "-local-host", "127.0.0.1",
                "Governo:br.pucpr.agentes.negociacao.lab16.Governo(1000,1500);" +
                        "EmpresaA:br.pucpr.agentes.negociacao.lab16.Empresa(2000,1000,0.1);" +
                        "EmpresaB:br.pucpr.agentes.negociacao.lab16.Empresa(1700,900,0.3);" +
                        "EmpresaC:br.pucpr.agentes.negociacao.lab16.Empresa(1700,900,0.5);" +
                        "EmpresaD:br.pucpr.agentes.negociacao.lab16.Empresa(1700,900,0.7);" +
                        "EmpresaE:br.pucpr.agentes.negociacao.lab16.Empresa(1700,900,0.9);" +
                        "EmpresaF:br.pucpr.agentes.negociacao.lab16.Empresa(1700,900,1.0);"

        };
        jade.Boot.main(init);
    }
}
