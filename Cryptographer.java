package encryptdecrypt;

public class Cryptographer {
    private Algorithm algorithm;
    private String mode;

    public Cryptographer(Algorithm unicodeAlgorithm, String mode) {
        this.algorithm = unicodeAlgorithm;
        this.mode = mode;
    }

    public void setEncryptionAlgorithm(Algorithm unicodeAlgorithm) {
        this.algorithm = unicodeAlgorithm;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String runEncryption(String message, int key) {
        if ("enc".equals(mode)) {
            return this.algorithm.encrypt(message, key);
        } else if ("dec".equals(mode)) {
            return this.algorithm.decrypt(message, key);
        }
        return "Wrong mode input!";
    }
}

interface Algorithm {
    String encrypt(String message, int key);

    String decrypt(String message, int key);
}

class UnicodeAlgorithm implements Algorithm {

    @Override
    public String encrypt(String message, int key) {
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

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, -key);
    }
}

class ShiftAlgorithm implements Algorithm {

    @Override
    public String encrypt(String message, int key) {
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        final String ALPHABET_UPPER = ALPHABET.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (ALPHABET.contains(String.valueOf(message.charAt(i)))) {
                try {
                    sb.append(ALPHABET.charAt((ALPHABET.indexOf(message.charAt(i)) + key) % 26));
                } catch (IndexOutOfBoundsException e) {
                    sb.append(ALPHABET.charAt((ALPHABET.indexOf(message.charAt(i)) + key) + 26));
                }
            } else if (ALPHABET_UPPER.contains(String.valueOf(message.charAt(i)))) {
                try {
                    sb.append(ALPHABET_UPPER.charAt((ALPHABET_UPPER.indexOf(message.charAt(i)) + key) % 26));
                } catch (IndexOutOfBoundsException e) {
                    sb.append(ALPHABET_UPPER.charAt((ALPHABET_UPPER.indexOf(message.charAt(i)) + key) + 26));
                }
            }
            else {
                sb.append(message.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, -key);
    }
}