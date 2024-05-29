
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
public class calcetis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] linha = sc.nextLine().split(" ");
        
        int precoAtual = Integer.parseInt(linha[0]);
        int numeroProdutos = Integer.parseInt(linha[1]);
        
        int diferenca = 200 - precoAtual;
        
        List<Integer> precos = new ArrayList();
        for (int i = 0; i < numeroProdutos; i++) {
            precos.add(sc.nextInt());
        }
        
        Collections.sort(precos, (i, j) -> Integer.compare(j, i));
        
        int soma = precos.get(0) + precos.get(1) + precos.get(2);
        if (soma == diferenca) {
            System.out.println("fretegratis");
            return;
        }
        
        for (int i = 3; i < precos.size(); i++) {
            soma -= precos.get(i - 3);
            
            soma += precos.get(i);
            
            if (soma == diferenca) {
                System.out.println("fretegratis");
                return;
            }
            
            if (soma < diferenca) {
                System.out.println("fretepago");
                return;
            }
        }
        
        System.out.println("fretepago");
    }
    
}
