package br.pucpr.agentes.negociacao.lab14;

public class LivrariaSMA {

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula11",
                "-local-host", "127.0.0.1",
                "Carlos:br.pucpr.agentes.negociacao.lab14.Vendedor(vendas,vendalivros);" +
                        "Mateus:br.pucpr.agentes.negociacao.lab14.Vendedor(vendas,vendaeletronicos);" +
                        "Joao:br.pucpr.agentes.negociacao.lab14.Comprador"};

        jade.Boot.main(init);
    }
}
