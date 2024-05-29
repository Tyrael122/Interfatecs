/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class pivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] linha = sc.nextLine().split(" ");
        List<Integer> numeros = new ArrayList();
        for (int i = 0; i < linha.length; i++) {
            numeros.add(Integer.parseInt(linha[i]));
        }
        
        Collections.sort(numeros);
        
        System.out.println(numeros.get(1));
    }
    
}
