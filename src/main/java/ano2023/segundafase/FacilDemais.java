package ano2023.segundafase;

import util.TestCaseReader;

public class FacilDemais {

    // Premissas:
    // O único número primo par é 2. Todos os outros números primos são ímpares.
    // Par + Par = Par.
    // Ímpar + Ímpar = Par.
    // Ímpar + Par = Ímpar.

    public static String main(TestCaseReader reader) {
        int quantidadeNumerosPrimos = reader.nextInt();

        int quantidadeDePares = 0;
        for (int i = 0; i < quantidadeNumerosPrimos; i++) {
            long posicaoNumeroPrimo = reader.nextUnsignedLong();
            if (posicaoNumeroPrimo == 1) {
                quantidadeDePares += 1;
            }
        }

        int quantidadeDeImpares = quantidadeNumerosPrimos - quantidadeDePares;
        if (isPar(quantidadeDeImpares)) { // Primeiro termo da soma é **par**.
            // Como só pode haver pares no conjunto restante,
            // só pode ser que o resultado será par + par = par.
            return "par";

        } else { // Primeiro termo da soma é **impar**.
            // Como não há mais ímpares no conjunto, a soma só pode ser impar + par = impar,
            // que somando com outro par vai dar impar de novo, e assim por diante.
            return "impar";
        }
    }

    private static boolean isPar(int numero) {
        return numero % 2 == 0;
    }
}
