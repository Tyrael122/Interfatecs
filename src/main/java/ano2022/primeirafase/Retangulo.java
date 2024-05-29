package ano2022.primeirafase;

import util.TestCaseReader;

public class Retangulo {
    private static long[][] matriz;

    public static String main(TestCaseReader reader) {
        int linhas = reader.nextInt();
        int colunas = reader.nextInt();

        matriz = new long[linhas][colunas];

        int linhaParaAchar = reader.nextInt();
        int colunaParaAchar = reader.nextInt();

        return String.valueOf(calcularValorCoordenada(linhaParaAchar - 1, colunaParaAchar - 1));
    }

    private static long calcularValorCoordenada(int linha, int coluna) {
        if (linha <= 1) {
            return 3;
        }

        if (coluna <= 0) {
            return 3;
        }

        if (matriz[linha][coluna] != 0) {
            return matriz[linha][coluna];
        }

        long valorCalculado = calcularValorCoordenada(linha - 1, coluna) +
                calcularValorCoordenada(linha, coluna - 1) +
                calcularValorCoordenada(linha - 1, coluna - 1);

        matriz[linha][coluna] = valorCalculado;

        return valorCalculado;
    }
}
