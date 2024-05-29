
import java.util.ArrayList;
import java.util.Arrays;
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
public class purrinha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numJogadores = sc.nextInt();
        String vazio = sc.nextLine();
        
        String[] jogadores = new String[numJogadores];
        for (int i = 0; i < numJogadores; i++) {
            jogadores[i] = sc.nextLine();
        }
        
        int numRodadas = sc.nextInt();        
        
        vazio = sc.nextLine();
        
        int[] pontuacaoJogadores = new int[numJogadores];
        
        for (int i = 0; i < numRodadas; i++) {
            List<Integer> jogadorGanhador = calcularGanhador(sc);
            
            for (int jogador : jogadorGanhador) {
                pontuacaoJogadores[jogador]++;   
            }
        }
        
        int maiorPontuacao = 0;
        int maiorJogador = 0;
        for (int i = 0; i < numJogadores; i++) {
            if (pontuacaoJogadores[i] > maiorPontuacao) {
                maiorPontuacao = pontuacaoJogadores[i];
                maiorJogador = i;
            }
        }
        
        Arrays.sort(pontuacaoJogadores);
        
        if (pontuacaoJogadores[pontuacaoJogadores.length - 1] == pontuacaoJogadores[pontuacaoJogadores.length - 2]) {
            System.out.println("EMPATE");
            return;
        }
        
        System.out.println(jogadores[maiorJogador] + " GANHOU");
    }

    private static List<Integer> calcularGanhador(Scanner sc) {
        String[] maos = sc.nextLine().split(" ");
        
        int somaMao = 0;
        for (String mao : maos) {
            somaMao += Integer.parseInt(mao);
        }
        
        String[] palpites = sc.nextLine().split(" ");
        
        List<Integer> jogadoresGanhadores = new ArrayList();
        for (int i = 0; i < palpites.length; i++) {
            int palpite = Integer.parseInt(palpites[i]);
            
            if (palpite == somaMao) {
                jogadoresGanhadores.add(i);
            }
        }
        
        return jogadoresGanhadores;
    }
}
