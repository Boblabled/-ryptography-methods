import java.math.BigInteger;

/**
 * @author Abramov Maxim
 * @date 09.04.2023 21:33
 */
public class Task6 {
    public static void run(String P, String Q, String G, String x, String k, String hm) {
        System.out.println("\nTASK 6:");
        DSA(new BigInteger(P), new BigInteger(Q), new BigInteger(G),
                new BigInteger(x), new BigInteger(k), new BigInteger(hm));
    }

    private static void DSA(BigInteger P, BigInteger Q, BigInteger G, BigInteger x, BigInteger k, BigInteger hm) {
        BigInteger Y = G.modPow(x,P);
        BigInteger R = G.modPow(k, P).mod(Q);
        BigInteger S = hm.add(x.multiply(R)).multiply(k.modInverse(Q)).mod(Q);
        System.out.println("Y: " + Y);
        System.out.println("R: " + R);
        System.out.println("S: " + S);
        BigInteger A = hm.multiply(S.modInverse(Q)).mod(Q);
        BigInteger B = R.multiply(S.modInverse(Q)).mod(Q);
        BigInteger V = G.modPow(A, P).multiply(Y.modPow(B, P)).mod(P).mod(Q);
        System.out.println("V: " + V);
        System.out.println((V.equals(R)) ? "Проверка пройдена" : "Проверка не пройдена");
    }
}
