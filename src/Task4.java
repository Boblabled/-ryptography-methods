import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Abramov Maxim
 * @date 09.04.2023 15:02
 */
public class Task4 {
    public static void run(String register, Integer[] polynomial, Integer[] key, int size, int tactTimes) {
        System.out.println("\nTASK 4:");
        System.out.println("\nsubTask 1:");
        countPeriod(register, polynomial);
        System.out.println("\nsubTask 2:");
        rc4(key, size, tactTimes);
    }

    private static void countPeriod(String register, Integer[] polynomial){
        String newRegister = register;
        StringBuilder bits = new StringBuilder();
        int n = 0;
        do {
            int bit = newRegister.charAt(newRegister.length() - polynomial[0]) - '0';
            for (int i = 1; i < polynomial.length; i++) {
                bit = bit ^ (newRegister.charAt(newRegister.length() - polynomial[i]) - '0');
            }
            bits.append(newRegister.charAt(newRegister.length()-1));
            bits.append(",");
            newRegister = bit + newRegister.substring(0, register.length() - 1);
            n++;
        } while (!newRegister.equals(register));
        bits.deleteCharAt(bits.length()-1);
        bits.reverse();
        System.out.println(n);
        System.out.println(bits);
    }

    private static void rc4(Integer[] key, int size, int tactTimes){
        ArrayList<Integer> s = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            s.add(i);
        }
        int j = 0;
        for (int i = 0; i < size; i++) {
            j = (j + s.get(i) + key[i % key.length]) % size;
            Collections.swap(s, i, j);
        }
        System.out.println(s.stream().map(String::valueOf).collect((Collectors.joining(","))));
        int i = 0;
        j = 0;
        ArrayList<Integer> k = new ArrayList<>();
        for (int l = 0; l < tactTimes; l++) {
            i = (i + 1) % size;
            j = (j + s.get(i)) % size;
            Collections.swap(s, i, j);
            k.add(s.get((s.get(i) + s.get(j)) % size));
        }
        Collections.reverse(k);
        System.out.println(k.stream().map(String::valueOf).collect((Collectors.joining(","))));
    }
}
