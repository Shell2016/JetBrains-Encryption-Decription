package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        scanner.close();

        Encrypt encrypt = new Encrypt(key, message);
        String encryptedMessage = encrypt.encrypt();
        System.out.println(encryptedMessage);
    }
}
