package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String mode = "enc";
    public static int key = 0;
    public static String data = "";
    public static String outputFile = null;


    public static void main(String[] args) {
        setParams(List.of(args));

        if (!"dec".equals(mode) && !"enc".equals(mode)) {
            System.out.println("Wrong input!");
        } else if ("dec".equals(mode)) {
            key = -key;
        }

        if (outputFile == null) {
            System.out.println(encrypt(data, key));
        } else {
            File file = new File(outputFile);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(encrypt(data, key));
            } catch (IOException e) {
                System.out.println("Error occurred! Some problems with output file");
            }
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

//    public static String decrypt(String message, int key) {
//        return encrypt(message, -key);
//    }

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
        } else if (argsList.contains("-in")) {
            try {
                String fileName = argsList.get(argsList.indexOf("-in") + 1);
                File file = new File(fileName);
                try (Scanner scanner = new Scanner(file)) {
                    if (scanner.hasNext()) {
                        data = scanner.nextLine();
                        System.out.println("Data " + data);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error occurred! Data file not found");
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }

        if (argsList.contains("-out")) {
            try {
                outputFile = argsList.get(argsList.indexOf("-out") + 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error occurred! Please enter name for output file");
            }
        }
    }

}
