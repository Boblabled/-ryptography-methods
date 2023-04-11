import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Abramov Maxim
 * @date 10.04.2023 21:28
 */
public class Exam {
    private static final ArrayList<Character> ALPHABET =
            "абвгдежзийклмнопрстуфхцчшщъыьэюя".chars()
                    .mapToObj(e->(char)e).collect(Collectors.toCollection(ArrayList::new));
    private static final int DIVIDER = 256;
    private static final int ASCIIOFFSET = 224;
    private static final String OUTPUTPATH = "data/decodedText.txt";

    public static void run(String intsPath, String remaindersPath, int d, int p, int q) {
        System.out.println("\nEXAM:");
        ArrayList<ArrayList<Integer>> ints = new ArrayList<>();
        ArrayList<ArrayList<Integer>> remainders = new ArrayList<>();
        readCSV(ints, intsPath);
        readCSV(remainders, remaindersPath);
        ArrayList<ArrayList<Integer>> encodedMatrix = new ArrayList<>();
        combine(encodedMatrix,ints,remainders);
        decode(encodedMatrix, d, p*q);
    }

    private static void combine(ArrayList<ArrayList<Integer>> matrix,
                                ArrayList<ArrayList<Integer>> ints,
                                ArrayList<ArrayList<Integer>> remainders) {
        for (int i = 0; i < ints.size(); i++) {
            ArrayList<Integer> line = new ArrayList<>();
            for (int j = 0; j < ints.size(); j++) {
                int num = ints.get(i).get(j)*DIVIDER + remainders.get(i).get(j);
                line.add(num);
            }
            matrix.add(line);
        }
    }

    private static void readCSV(ArrayList<ArrayList<Integer>> matrix, String path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                matrix.add(Arrays.stream(line.split(",")).map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void decode(ArrayList<ArrayList<Integer>> encodedMatrix, int d, int n){
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(OUTPUTPATH))) {
            for (ArrayList<Integer> line : encodedMatrix) {
                for (Integer num : line) {
                    int k = pow(num, d, n);
                    switch (k) {
                        case (32) -> bufferedWriter.write(" ");
                        case (10) -> bufferedWriter.newLine();
                        default -> bufferedWriter.write(ALPHABET.get(k - ASCIIOFFSET).toString());
                    }
                }
            }
            System.out.println("Расшифрованный текст сохранён в: " + OUTPUTPATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int pow(int a, int n, int mod){
        return BigInteger.valueOf(a).modPow(BigInteger.valueOf(n), BigInteger.valueOf(mod)).intValue();
    }
}
