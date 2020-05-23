public class SistemaMultiAgente {

    public static void main(String[] args) {
        String[] parametros01 = {"-gui", "-name", "lab03", "-local-host", "127.0.0.1"};
        jade.Boot.main(parametros01);

        String[] parametros02 = {"-gui", "-container", "-host", "127.0.0.1"};
        jade.Boot.main(parametros02);
    }
    
}
