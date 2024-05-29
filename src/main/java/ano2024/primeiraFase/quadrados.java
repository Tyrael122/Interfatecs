/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class quadrados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int quadradoPerfeito = sc.nextInt();
        
        int somaImpares = 0;
        int impar = 1;
        
        int contador = 0;
        while (somaImpares < quadradoPerfeito) {
            somaImpares += impar;
            
            impar += 2;
            
            contador++;
        }
        
        System.out.println(contador);
    }
    
}
