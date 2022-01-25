package encryptdecrypt;

import java.util.ArrayList;
import java.util.List;

public class Encrypt {
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private final int ALPHABET_SIZE = ALPHABET.length();
    private final int key;
    private String message;

    public Encrypt(int key, String message) {
        this.key = key;
        this.message = message;
    }

    private List<Character> createAlphabetList(String alphabet) {
        List<Character> alphabetList = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            alphabetList.add(alphabet.charAt(i));
        }
        return alphabetList;
    }

    public String encrypt() {
        List<Character> alphabetList = createAlphabetList(ALPHABET);
        List<Character> encryptedList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(message.length());

        String[] words = message.split(" ");
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (alphabetList.contains(word.charAt(i))) {
                    try {
                        encryptedList.add(alphabetList.get(alphabetList.indexOf(word.charAt(i)) + key));
                    } catch (IndexOutOfBoundsException e) {
                        encryptedList.add(alphabetList.get(alphabetList.indexOf(word.charAt(i)) + key - ALPHABET_SIZE));
                    }
                } else {
                    encryptedList.add(word.charAt(i));
                }
            }
            for (Character ch : encryptedList) {
                sb.append(ch);
            }
            encryptedList.clear();
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
