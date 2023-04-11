import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Abramov Maxim
 * @date 09.04.2023 18:00
 */
public class Task5 {
    private static final ArrayList<Character> ALPHABET =
            "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя".chars()
                    .mapToObj(e->(char)e).collect(Collectors.toCollection(ArrayList::new));
    private static final int ASCIIOFFSET = 192;
    private static final int LETTERSIZE = 4;

    public static void run(String messageToEncode, int p, int q, int c, String messageToDecode, int d, int n) {
        System.out.println("\nTASK 5:");
        System.out.println("\nsubTask 1:");
        encode(messageToEncode, p, q, c);
        System.out.println("\nsubTask 2:");
        decode(messageToDecode,d, n);
    }

    private static void encode(String message, int p, int q, int c) {
        int n = p * q;
        int fn = (p - 1) * (q - 1);
        int d = BigInteger.valueOf(c).modInverse(BigInteger.valueOf(fn)).intValue();
        System.out.println("d: " + d);
        System.out.println("N: " + n);
        StringBuilder asciiMessage = new StringBuilder();
        StringBuilder encodedMessage = new StringBuilder();
        for (char letter : message.toCharArray()) {
            int asciiId = ALPHABET.indexOf(letter) + ASCIIOFFSET;
            asciiMessage.append(asciiId);
            encodedMessage.append(addZeroes(pow(asciiId, c, n) + ""));
        }
        System.out.println(asciiMessage);
        System.out.println(encodedMessage);
    }

    private static String addZeroes(String encodedLetter) {
        return ("0".repeat(LETTERSIZE) + encodedLetter).substring(encodedLetter.length());
    }

    private static int pow(int a, int n, int mod){
        return BigInteger.valueOf(a).modPow(BigInteger.valueOf(n), BigInteger.valueOf(mod)).intValue();
    }

    private static void decode(String message, int d, int n){
        int l = 0;
        int r = LETTERSIZE;
        StringBuilder decodedMessage = new StringBuilder();
        while(r <= message.length()) {
            int i = pow(Integer.parseInt(message.substring(l, r)), d, n);
            decodedMessage.append(ALPHABET.get(i - ASCIIOFFSET));
            l = r;
            r += LETTERSIZE;
        }
        System.out.println(decodedMessage);
    }
}
