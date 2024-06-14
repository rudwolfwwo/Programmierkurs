package Nim;

import java.util.Arrays;
import java.util.Scanner;

public class Nim {
    boolean userMove = true;
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Willkommen zum Spiel Nim!");
        new Nim(new int[] {20,20,20});
        //int a = 16;
        //System.out.println(String.format("%" + a + "s",Integer.toBinaryString(5)).replace(' ','0'));
        //System.out.println(Integer.parseInt("1111111111111111",2));
    }
    public Nim(int[] stapel) {
        int auswahl;
        while (Arrays.stream(stapel).anyMatch(i -> i != 0)) {
            System.out.print("\nAktuelles Spielfeld: ");
            Arrays.stream(stapel).forEach(i -> System.out.print("\t " + i));
            if (userMove) {
                try {
                    System.out.print("\nVon welchem Stapel willst du Knöpfe nehmen? ");
                    auswahl = switch (Integer.parseInt(sc.nextLine())) {
                        case (1) -> 0;
                        case (2) -> 1;
                        case (3) -> 2;
                        default -> throw new NumberFormatException("Es gibt nur die Stapel 1 bis " + stapel.length + "!");
                    };
                    if (stapel[auswahl] == 0) throw new NumberFormatException("Dieser Stapel hat keine Knöpfe mehr");
                    System.out.println("Wie viele Knöpfe willst du vom " + (auswahl+1) + ". Stapel nehmen? (1 bis " + stapel[auswahl] + ")");
                    int i = Integer.parseInt(sc.nextLine());
                    if (i < 1 || i > stapel[auswahl]) throw new NumberFormatException("Es müssen 1 bis " + stapel[auswahl] + " Knöpfe sein!");
                    stapel[auswahl] -= i;
                    userMove = false;
                } catch (NumberFormatException n) {
                    System.err.println(n.getMessage());

                }
            } else {
                int hilf;
                   if (stapel[0] >= stapel[1] && stapel[0] >= stapel[2]) {
                       hilf = calculateNextMove(stapel[1],stapel[2],stapel[0]);
                       stapel[0] -= hilf;
                       auswahl = 1;
                   }
                   else if (stapel[1] >= stapel[0] && stapel[1] >= stapel[2]) {
                       hilf = calculateNextMove(stapel[0],stapel[2],stapel[1]);
                       stapel[1] -= hilf;
                       auswahl = 2;
                   }
                   else {
                       hilf = calculateNextMove(stapel[0],stapel[1],stapel[2]);
                       stapel[2] -= hilf;
                       auswahl = 3;
                   }
                   System.out.println("\nDer Computer hat " + (hilf > 1 ? hilf + " Knöpfe" : "einen Knopf") + " vom " + auswahl + ". Stapel genommen!");
                   userMove = !userMove;
            }
        }
        System.out.println((userMove ? "Der Computer hat " : "Du hast ") +  "gewonnen!");
    }
    public static int calculateNextMove(int x, int y, int biggest) {
        String a = Integer.toBinaryString(x);
        String b = Integer.toBinaryString(y);
        /*String ausgleich = (a.length() > b.length() ? a : b).substring(0,Math.abs(a.length() - b.length()));
        if (a.length() > b.length()) {
            a = a.substring(ausgleich.length());
        } else {
            b = b.substring(ausgleich.length());
        }*/
        String sum = "";
        if (a.length() > b.length()) b = String.format("%" + a.length() + "s",b).replace(' ','0');
        else a = String.format("%" + b.length() + "s",a).replace(' ','0');
        for (int i = 0; i < a.length(); i++) {sum = a.charAt(i) == b.charAt(i) ? sum + 0 : sum + 1;}
        return biggest - Integer.parseInt(sum,2) == 0 ?  1 : Math.abs(biggest - Integer.parseInt(sum,2));
    }
}
