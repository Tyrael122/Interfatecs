
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aluno
 */
public class mapacalor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numeroMatrizes = sc.nextInt();
        
        String vazio = sc.nextLine();

        List<int[][]> matrizes = new ArrayList();
        for (int i = 0; i < numeroMatrizes; i++) {
            matrizes.add(lerMatriz(sc));
        }
        
        int somaSuperior = 0;
        int somaEsq = 0;
        int somaCentro = 0;
        int somaDir = 0;
        int somaInferior = 0;
        
        for (int[][] matriz : matrizes) {
            somaSuperior = somaSuperior + somaSuperior(matriz);
            somaEsq = somaEsq + somaEsq(matriz);
            somaCentro = somaCentro + somaCentro(matriz);
            somaDir = somaDir + somaDir(matriz);
            somaInferior = somaInferior + somaInferior(matriz);
        }
        
        List<Integer> somas = new ArrayList(List.of(somaSuperior, somaEsq, somaCentro, somaDir, somaInferior));
        
        Collections.sort(somas);
        
        int maiorSoma = somas.get(somas.size() - 1); // Pega o ultimo, que eh o maior
        if (somaSuperior == maiorSoma) {
            System.out.println("superior");
            return;
        }
        
        if (somaEsq == maiorSoma) {
            System.out.println("esquerda");
            return;
        }
        
        if (somaCentro == maiorSoma) {
            System.out.println("centro");
            return;
        }
        
        if (somaDir == maiorSoma) {
            System.out.println("direita");
            return;
        }
        
        if (somaInferior == maiorSoma) {
            System.out.println("inferior");
            return;
        }       
    }

    private static int[][] lerMatriz(Scanner sc) {
        int[][] matriz = new int[6][3];
        
        for (int i = 0; i < 6; i++) {
            int[] numeros = lerNumeros(sc);
            
            matriz[i] = numeros;
        }
        
        return matriz;
    }

    private static int[] lerNumeros(Scanner sc) {
        String[] linha = sc.nextLine().split(" ");
        
        int[] numeros = new int[3];
        
        for (int i = 0; i < linha.length; i++) {
            numeros[i] = Integer.parseInt(linha[i]);
        }
        
        return numeros;
    }

    private static int somaSuperior(int[][] matriz) {
        int soma = 0;
        
        for (int i = 0; i < 3; i++) {
            soma += matriz[0][i];
        }
        
        return soma;
    }

    private static int somaEsq(int[][] matriz) {
        int soma = 0;
        
        for (int i = 1; i < 4; i++) {
            soma += matriz[i][0];
        }
        
        return soma;
    }

    private static int somaCentro(int[][] matriz) {
        int soma = 0;
        
        for (int i = 1; i < 4; i++) {
            soma += matriz[i][1];
        }
        
        return soma;
    }

    private static int somaDir(int[][] matriz) {
        int soma = 0;
        
        for (int i = 1; i < 4; i++) {
            soma += matriz[i][2];
        }
        
        return soma;
    }

    private static int somaInferior(int[][] matriz) {
        int soma = 0;
        
        for (int i = 0; i < 3; i++) {
            soma += matriz[5][i];
        }
        
        return soma;
    }
}
