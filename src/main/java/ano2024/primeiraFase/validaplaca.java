
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
public class validaplaca {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String string = sc.nextLine();
        
        if (valida41(string)) {
            System.out.println("Placa muito antiga");
            return;
        }
        
        if (valida69(string)) {
            System.out.println("Placa numerica");
            return;
        }
        
        if (valida90(string)) {
            System.out.println("Placa AA-9999");
            return;
        }
        
        if (valida2018(string)) {
            System.out.println("Placa AAA-9999");
            return;
        }
        
        if (validaMercosul(string)) {
            System.out.println("Placa Mercosul");
            return;
        }
        
        System.out.println("Placa invalida");
    }

    private static boolean valida41(String string) {
        if (string.length() < 2) return false;  
        if (string.length() > 6) return false;
        
        boolean isLetraValida = string.charAt(0) == 'A' || string.charAt(0) == 'P';
        if (!isLetraValida) {
            return false;
        }
        
        for (int i = 1; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        
        return true;
    }

    private static boolean valida69(String string) {
        if (string.length() > 7) return false;
        if (string.length() < 1) return false;
        
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        
        return true;
    }

    private static boolean valida90(String string) {
        if (string.length() != 6) return false;
        
        for (int i = 0; i < 2; i++) {
            if (!Character.isLetter(string.charAt(i))) return false;
        }
        
        
        for (int i = 2; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        
        return true;
    }

    private static boolean valida2018(String string) {
        if (string.length() != 7) return false;
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(string.charAt(i))) return false;
        }
        
        
        for (int i = 3; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        
        return true;
    }

    private static boolean validaMercosul(String string) {
        if (string.length() != 7) return false;
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(string.charAt(i))) return false;
        }
        
        if (!Character.isDigit(string.charAt(3))) return false;
        
        if (!Character.isLetter(string.charAt(4))) return false;
        
        for (int i = 5; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        
        return true;
    }
    
}
