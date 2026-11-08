import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ExperimentRunner {

    public static void main(String[] args) {
        String csvFile = "results.csv";

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            pw.println("algorithm,input_size,time_ms,op_count");

            EditDistance ed = new EditDistance();
            StringSearch ss = new StringSearch();

            int[] sizes = {10, 20, 50, 100, 200, 500};

            for (int n : sizes) {
                String text = generateRandomString(n);
                String pattern = generateRandomString(Math.max(2, n / 5));

                long start = System.nanoTime();
                ss.naiveSearch(text, pattern);
                long timeNaive = (System.nanoTime() - start) / 1_000;
                pw.println("NaiveSearch," + n + "," + timeNaive + "," + ss.getOpCount());

                start = System.nanoTime();
                ss.kmpSearch(text, pattern);
                long timeKmp = (System.nanoTime() - start) / 1_000;
                pw.println("KMPSearch," + n + "," + timeKmp + "," + ss.getOpCount());

                String s1 = generateRandomString(n / 2);
                String s2 = generateRandomString(n / 2);

                start = System.nanoTime();
                ed.distanceTable(s1, s2);
                long timeTable = (System.nanoTime() - start) / 1_000;
                pw.println("EditDistanceTable," + (n / 2) + "," + timeTable + "," + ed.getOpCount());

                start = System.nanoTime();
                ed.distanceTwoRows(s1, s2);
                long timeRows = (System.nanoTime() - start) / 1_000;
                pw.println("EditDistanceTwoRows," + (n / 2) + "," + timeRows + "," + ed.getOpCount());
            }

            System.out.println("Эксперименты успешно завершены. Результаты записаны в " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomString(int length) {
        String alphabet = "AB";
        Random random = new Random(42);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}