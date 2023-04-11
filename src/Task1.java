/**
 * @author Abramov Maxim
 * @date 08.04.2023 16:08
 */
public class Task1 {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void run(String wordToEncode, String wordToDecode, String key){
        System.out.println("\nTASK 1:\n");
        System.out.println(encodeWord(wordToEncode, key));
        System.out.println(decodeWord(wordToDecode, key));
    }

    private static String encodeWord(String word, String key){
        char[] encodedWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++){
            encodedWord[i] = (ALPHABET.charAt(encodeFormula(word, i, key)));
        }
        return new String(encodedWord);
    }

    private static int encodeFormula(String word,  int i, String key) {
        return (ALPHABET.indexOf(word.charAt(i)) +
                ALPHABET.indexOf(key.charAt(i % key.length()))) %
                ALPHABET.length();
    }

    private static String decodeWord(String word, String key){
        char[] decodedWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++){
            decodedWord[i] = (ALPHABET.charAt(decodeFormula(word, i, key)));
        }
        return new String(decodedWord);
    }

    private static int decodeFormula(String word,  int i, String key) {
        return (ALPHABET.indexOf(word.charAt(i)) +
                ALPHABET.length() -
                ALPHABET.indexOf(key.charAt(i % key.length()))) %
                ALPHABET.length();
    }
}
