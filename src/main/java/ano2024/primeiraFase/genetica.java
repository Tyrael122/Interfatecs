/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;

public class genetica
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int tamanhoPalindromo = sc.nextInt();
        
        String vazio = sc.nextLine();
        
        String string = sc.nextLine();
        String possivelPalindromo = string.substring(0, tamanhoPalindromo);
        
        for (int i = 0; i < string.length() - tamanhoPalindromo + 1; i++) {
            possivelPalindromo = string.substring(i, tamanhoPalindromo + i);
            
            if (possivelPalindromo.equals(inverter(possivelPalindromo))) {
                System.out.println("S");
                return;
            }
        }
        
        System.out.println("N");
    }   

    private static String inverter(String possivelPalindromo) {
        String stringInvertida = "";
        
        for (int i = possivelPalindromo.length() - 1; i >= 0; i--) {
            stringInvertida += possivelPalindromo.charAt(i);
        }    
        
        return stringInvertida;
    }
}
