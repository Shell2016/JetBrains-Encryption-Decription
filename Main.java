package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String mode = scanner.nextLine().trim();
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        scanner.close();

        switch (mode) {
            case "enc":
                System.out.println(encrypt(message, key));
                break;
            case "dec":
                System.out.println(decrypt(message, key));
                break;
            default:
                System.out.println("Wrong input!");
        }
    }

    public static String encrypt(String message, int key) {
        StringBuilder sb = new StringBuilder();
        final int asciiStart = 32;
        final int asciiEnd = 126;
        final int asciiShift = 95;
        int ascii;
        for (int i = 0; i < message.length(); i++) {
            ascii = message.charAt(i) + key;
            if (ascii > asciiEnd)
                ascii = message.charAt(i) + key - asciiShift;
            if (ascii < asciiStart)
                ascii = message.charAt(i) + key + asciiShift;
            sb.append((char) ascii);
        }
        return sb.toString();
    }

    public static String decrypt(String message, int key) {
        return encrypt(message, -key);
    }

}
