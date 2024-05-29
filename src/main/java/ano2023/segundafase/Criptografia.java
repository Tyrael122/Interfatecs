package ano2023.segundafase;

import util.TestCaseReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Criptografia {
    public static String main(TestCaseReader reader) {
        List<Usuario> usuarios = new ArrayList<>();

        String linha = reader.nextString();
        while (!linha.equals("ACABOU")) {
            String nome = linha;
            String senhaPura = reader.nextString();

            String senhaCriptografada = hashPassword(senhaPura);
            usuarios.add(new Usuario(nome, senhaCriptografada));

            linha = reader.nextString();
        }

        usuarios.sort(Comparator.comparing(Usuario::nome));

        StringBuilder sb = new StringBuilder();
        for (Usuario usuario : usuarios) {
            sb.append("usuario...: ").append(usuario.nome()).append("\n");
            sb.append("valor hash: ").append(usuario.senha()).append("\n");

            sb.append("------------------------------\n");
        }

        return sb.toString();
    }

    private static String hashPassword(String senhaPura) {
        long s = calculateS(senhaPura);

        List<Integer> primeFactors = calculatePrimeFactors(s);

        StringBuilder sb = new StringBuilder();
        sb.append(s);

        for (long primeFactor : primeFactors) {
            sb.append(primeFactor);
        }

        return sb.toString();
    }

    private static long calculateS(String senhaPura) {
        int[] asciiCodes = senhaPura.chars().toArray();

        long s = 0;
        for (int i = 0; i < asciiCodes.length; i++) {
            int multiplication = asciiCodes[i] * (i + 1);

            s += multiplication;
        }

        return s;
    }

    private static List<Integer> calculatePrimeFactors(long numero) {
        List<Integer> fatoresPrimos = new ArrayList<>();
        while (numero % 2 == 0) {
            fatoresPrimos.add(2);

            numero /= 2;
        }

        for (int divisor = 3; numero != 1; divisor += 2) {
            while (numero % divisor == 0) {
                fatoresPrimos.add(divisor);

                numero /= divisor;
            }
        }

        return fatoresPrimos;
    }
}

record Usuario(String nome, String senha) {
}
