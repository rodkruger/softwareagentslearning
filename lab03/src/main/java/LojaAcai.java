public class LojaAcai {

    public static void main(String[] args) {
        String[] parametros01 = {"-gui", "-name", "lojaacai.com.br", "-local-host", "127.0.0.1", "-container-name", "sede", "Gerente:jade.core.Agent"};
        jade.Boot.main(parametros01);

//        String[] parametros02 = {"-gui", "-container", "-container-name", "sede", "-host", "127.0.0.1", "Gerente:jade.core.Agent"};
//        jade.Boot.main(parametros02);

        String[] parametros03 = {"-gui", "-container", "-container-name", "Curitiba", "-host", "127.0.0.1", "Revendedor:jade.core.Agent"};
        jade.Boot.main(parametros03);

        String[] parametros04 = {"-gui", "-container", "-container-name", "São Paulo", "-host", "127.0.0.1", "Contador:jade.core.Agent"};
        jade.Boot.main(parametros04);

        String[] parametros05 = {"-gui", "-container", "-container-name", "Belém", "-host", "127.0.0.1", "Fornecedor:jade.core.Agent;Transportadora:jade.core.Agent"};
        jade.Boot.main(parametros05);


    }

}
