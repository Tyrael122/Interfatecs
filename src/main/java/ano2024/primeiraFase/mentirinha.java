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
public class mentirinha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numero = sc.nextInt();
        
        double raizQuadrada = Math.sqrt(numero);
        
        int numerosDivisores = 1; // Comeca com um pq divide por ele mesmo
        for (double i = raizQuadrada; i > 0; i--) {
            if (numero % i == 0) numerosDivisores++;
            
            if (numerosDivisores > 3) {
                System.out.println("nao");
                return;
            }
        }
        
        if (numerosDivisores == 3) {
            System.out.println("sim");
            return;
        }
        
        System.out.println("nao");
    }
}
