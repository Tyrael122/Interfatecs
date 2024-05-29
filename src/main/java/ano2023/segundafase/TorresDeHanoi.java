package ano2023.segundafase;

import util.TestCaseReader;

public class TorresDeHanoi {
    public static String main(TestCaseReader reader) {
        int quantidadeNumeros = reader.nextInt();

        int somaAtual = 0;
        int objetivo = 0;
        for (int i = 0; i < quantidadeNumeros; i++) {
            int numero = reader.nextInt();

            if (numero == 1) {
                somaAtual += (int) Math.pow(2, quantidadeNumeros - i - 1);
            }

            objetivo += (int) Math.pow(2, quantidadeNumeros - i - 1);
        }

        return String.valueOf(objetivo - somaAtual);
    }
}
