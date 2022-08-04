import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Calc {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        boolean rom = checkOnRom(str);
        calculate(rom, str);
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C" };
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100 };
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    private static void calculate(boolean rom, String str) {
        String[] words = str.split(" ");
        if (rom) {
            switch (words[1]) {
                case "+":
                    System.out.println(intToRoman(romToInt(words[0]) + romToInt(words[2])));
                    break;
                case "-":
                    if (romToInt(words[0]) > romToInt(words[2])) {
                        System.out.println(intToRoman(romToInt(words[0]) - romToInt(words[2])));
                        break;
                    } else {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("incorrect values");
                        }
                        System.exit(1);
                    }
                case "*":
                    System.out.println(intToRoman(romToInt(words[0]) * romToInt(words[2])));
                    break;
                case "/":
                    if (romToInt(words[0]) > romToInt(words[2])) {
                        System.out.println(intToRoman(romToInt(words[0]) / romToInt(words[2])));
                        break;
                    } else {
                        try {
                            throw new IOException();
                        } catch (IOException e) {
                            System.out.println("incorrect values");
                        }
                        System.exit(1);
                    }
            }
            } else
                switch (words[1]) {
                    case "+":
                        System.out.println(parseInt(words[0]) + parseInt(words[2]));
                        break;
                    case "-":
                        System.out.println(parseInt(words[0]) - parseInt(words[2]));
                        break;
                    case "*":
                        System.out.println(parseInt(words[0]) * parseInt(words[2]));
                        break;
                    case "/":
                        if (!words[2].equals("0")) {
                            System.out.println(parseInt(words[0]) / parseInt(words[2]));
                            break;
                        } else {
                            try {
                                throw new ArithmeticException();
                            } catch (ArithmeticException e) {
                                System.out.println("on null division");
                            }
                            System.exit(1);
                        }
                }

        }
    private static int romToInt(String word) {
        int res = 0;
        for (RomanNumbers r: RomanNumbers.values()) {
            if (word.equals(r.toString())) res = r.ordinal() + 1;
        }
        return res;
    }

    private static boolean checkOnRom(String str) {
        String[] words = str.split(" ");
        //System.out.print(words.length + "\n");

        if (words.length != 3) {
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
        if (arabNum.contains(words[0])) {
            isRom = false;
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
        if (!(!isRom && arabNum.contains(words[2]))
                && !(isRom && romNum.contains(words[2]))) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("incorrect second operand");
            }
            System.exit(1);
        }
        return isRom;
    }
}
