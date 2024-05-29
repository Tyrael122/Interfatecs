package ano2023.segundafase;

import util.TestCaseReader;

public class Impressora3D {
    private final int[] alturaDeCadaPonto;
    private final int alturaImpressora;
    private int alturaMaximaAtingida;


    public Impressora3D(int largura, int altura) {
        this.alturaDeCadaPonto = new int[largura + 1];

        this.alturaImpressora = altura;
    }

    public void imprimir(int inicio, int fim, int quantidadeMaterial) {
        alturaDeCadaPonto[inicio] += quantidadeMaterial; // sobe degrau
        alturaDeCadaPonto[fim] -= quantidadeMaterial; // desce degrau
    }

    public int getAlturaMaximaAtingida() {
        return alturaMaximaAtingida;
    }

    public boolean ultrapassouAlturaMaxima() {
        int alturaAtual = 0;
        for (int i : alturaDeCadaPonto) {
            alturaAtual += i;

            alturaMaximaAtingida = Math.max(alturaAtual, alturaMaximaAtingida);

            if (alturaMaximaAtingida > alturaImpressora) {
                return true;
            }
        }

        return false;
    }

    public static String main(TestCaseReader reader) {
        int largura = reader.nextInt();
        int altura = reader.nextInt();

        Impressora3D impressora3D = new Impressora3D(largura, altura);

        int numeroComandos = reader.nextInt();
        for (int i = 0; i < numeroComandos; i++) {
            int inicio = reader.nextInt();
            int fim = reader.nextInt();

            int quantidadeMaterial = reader.nextInt();

            impressora3D.imprimir(inicio, fim, quantidadeMaterial);
        }

        if (impressora3D.ultrapassouAlturaMaxima()) {
            return "invalida";
        }

        return String.valueOf(impressora3D.getAlturaMaximaAtingida());
    }
}
