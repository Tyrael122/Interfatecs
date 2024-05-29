/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class teclado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int quantidadePalavras = sc.nextInt();
        String vazio = sc.nextLine();
        
        List<String> palavras = new ArrayList();
        for (int i = 0; i < quantidadePalavras; i++) {
            palavras.add(sc.nextLine());
        }
        
        for (String palavra : palavras) {
            String numero = "";
            for (char letra : palavra.toCharArray()) {
                numero = numero + converterLetraParaNumero(letra);
            }
            
            System.out.println(numero);
        }
    }
    
    public static String converterLetraParaNumero(char letraChar) {
        String letra = Character.toString(letraChar);
        if ("ABC".contains(letra)) return "2";
        if ("DEF".contains(letra)) return "3";
        if ("GHI".contains(letra)) return "4";
        if ("JKL".contains(letra)) return "5";
        if ("MNO".contains(letra)) return "6";
        if ("PQRS".contains(letra)) return "7";
        if ("TUV".contains(letra)) return "8";
        if ("WXYZ".contains(letra)) return "9";
        
        throw new IllegalStateException("Nao deveria ter chegado aqui");
    }
}
