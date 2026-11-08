import java.util.ArrayList;
import java.util.List;

public class StringSearch {
    private int opCount = 0;

    public int getOpCount() {
        return opCount;
    }


    public List<Integer> naiveSearch(String text, String pattern) {
        opCount = 0;
        List<Integer> matches = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                opCount++;
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                matches.add(i);
            }
        }
        return matches;
    }


    public int[] lps(String pattern) {
        int m = pattern.length();
        int[] lpsArr = new int[m];
        int length = 0;
        int i = 1;

        lpsArr[0] = 0;

        while (i < m) {
            opCount++;
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lpsArr[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lpsArr[length - 1];
                } else {
                    lpsArr[i] = 0;
                    i++;
                }
            }
        }
        return lpsArr;
    }


    public List<Integer> kmpSearch(String text, String pattern) {
        opCount = 0;
        List<Integer> matches = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = text.length();
        int m = pattern.length();
        int[] lpsArr = lps(pattern);

        int i = 0;
        int j = 0;
        while (i < n) {
            opCount++;
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                matches.add(i - j);
                j = lpsArr[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lpsArr[j - 1];
                } else {
                    i++;
                }
            }
        }
        return matches;
    }
}