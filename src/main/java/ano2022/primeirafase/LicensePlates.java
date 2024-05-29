package ano2022.primeirafase;

import util.TestCaseReader;

import java.util.ArrayList;
import java.util.List;

public class LicensePlates {
    public static String main(TestCaseReader reader) {
        List<int[]> placas = parsePlacas(reader);

        StringBuilder sb = new StringBuilder();
        for (int[] placa : placas) {
            sb.append(getPlacaType(placa)).append("\n");
        }

        return sb.toString();
    }

    private static String getPlacaType(int[] placa) {
        for (int i = 0; i < 3; i++) {
            if (!isAsciiLetter(placa[i])) {
                return "ERRO";
            }
        }

        if (isAsciiNumber(placa[3]) && isAsciiLetter(placa[4]) && isAsciiNumber(placa[5]) && isAsciiNumber(placa[6])) {
            return "MERCOSUL";
        }

        for (int i = 3; i < 7; i++) {
            if (!isAsciiNumber(placa[i])) {
                return "ERRO";
            }
        }

        return "ANTIGA";
    }

    private static boolean isAsciiNumber(int number) {
        return number >= 48 && number <= 57;
    }

    private static boolean isAsciiLetter(int number) {
        return number >= 65 && number <= 90;
    }

    private static List<int[]> parsePlacas(TestCaseReader reader) {
        List<int[]> placas = new ArrayList<>();
        while (reader.hasNext()) {
            int[] placa = new int[7];
            for (int i = 0; i < 7; i++) {
                placa[i] = reader.nextInt();
            }

            placas.add(placa);
        }
        return placas;
    }
}
