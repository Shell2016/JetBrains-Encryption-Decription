package encryptdecrypt;

import java.util.List;

public class Main {
    static String mode = "enc";
    static int key = 0;
    static String data = "";

    public static void main(String[] args) {
        List<String> argsList = List.of(args);
        setParams(argsList);

        switch (mode) {
            case "enc":
                System.out.println(encrypt(data, key));
                break;
            case "dec":
                System.out.println(decrypt(data, key));
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

    public static void setParams(List<String> argsList) {
        if (argsList.contains("-mode")) {
            try {
                mode = argsList.get(argsList.indexOf("-mode") + 1);
                if (!"dec".equals(mode))
                    mode = "enc";
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong mode input");
            }
        }
        if (argsList.contains("-key")) {
            try {
                key = Integer.parseInt(argsList.get(argsList.indexOf("-key") + 1));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Wrong key input");
            }
        }

        if (argsList.contains("-data")) {
            try {
                data = argsList.get(argsList.indexOf("-data") + 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong data input");
            }
        }
    }

}
