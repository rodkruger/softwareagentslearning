package br.pucpr.agentes;

public class SMA {

    public static void main(String[] args) {
        String[] init = {
                "-gui",
                "-name", "aula02",
                "-local-host", "127.0.0.1",
                "Siri:br.pucpr.agentes.lab05.Siri(11,Sábado)"};
        jade.Boot.main(init);
    }
    
}
