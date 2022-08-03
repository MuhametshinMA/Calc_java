import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Calc {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        checkOnCorrect(str);
    }

    private static void checkOnCorrect(String str) {
        String[] words = str.split(" ");
        int i = 0;
        for (String word: words) {
            System.out.print(word + "\n");
            i++;
        }
        System.out.print(i + "\n");

        if (i != 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("incorrect input string");
            }
            System.exit(1);
        }
        Set<String> romNum = Set.of("I", "II", "III", "IV", "V", "VI", "VII",
                "VIII", "IX", "X");
        Set<String> arabNum = Set.of("1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10");
        Set<String> operators = Set.of("+", "-", "*", "/");

        boolean isRom = false;
        boolean isArab = false;
        if (arabNum.contains(words[0])) {
            isArab = true;
        } else if (romNum.contains(words[0])) {
            isRom = true;
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("incorrect first operand");
            }
            System.exit(1);
        }

        if (!operators.contains(words[1])) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("incorrect operator");
            }
            System.exit(1);
        }

        if (isArab && arabNum.contains(words[2])) {
            //isDone = true;
        } else if (isRom && romNum.contains(words[2])) {
            //isDone = true;
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("incorrect second operand");
            }
            System.exit(1);
        }
        //return isDone;
    }
}
