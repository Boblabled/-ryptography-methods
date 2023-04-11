/**
 * @author Abramov Maxim
 * @date 08.04.2023 17:54
 */
public class Task2 {
    public static void run(String m, String key1, String key2){
        System.out.println("\nTASK 2:\n");
        System.out.println(round(round(m, key1), key2));
    }

    private static String round(String m, String key){
        int l = Integer.parseInt(m.substring(0, m.length()/2), 2);
        int r = Integer.parseInt(m.substring(m.length()/2), 2);
        int k = Integer.parseInt(key, 2);
        StringBuilder ans = new StringBuilder(Integer.toBinaryString(r) + Integer.toBinaryString(l ^ k ^ r));
        while (ans.length() < m.length()) {
            ans.insert(0, "0");
        }
        return ans.toString();
    }
}
