package ano2023.segundafase;

import util.TestCaseReader;

public class Pim {
    public static String main(TestCaseReader reader) {
        int limite = reader.nextInt();

        StringBuilder resultado = new StringBuilder();
        for (int i = 1; i <= limite; i++) {
            if (ehMultiploDeQuatro(i)) {
                resultado.append("pim ");
            } else {
                resultado.append(i).append(" ");
            }
        }

        return resultado.toString().trim();
    }

    private static boolean ehMultiploDeQuatro(int numero) {
        return numero % 4 == 0;
    }
}
