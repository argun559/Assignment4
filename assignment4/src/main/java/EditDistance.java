public class EditDistance {
    private int opCount = 0;

    public int getOpCount() {
        return opCount;
    }


    public int distanceRecursive(String s1, String s2) {
        opCount = 0;
        return recHelper(s1, s2, s1.length(), s2.length());
    }

    private int recHelper(String s1, String s2, int i, int j) {
        opCount++;
        if (i == 0) return j;
        if (j == 0) return i;

        int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

        int insert = recHelper(s1, s2, i, j - 1) + 1;
        int delete = recHelper(s1, s2, i - 1, j) + 1;
        int substitute = recHelper(s1, s2, i - 1, j - 1) + cost;

        return Math.min(Math.min(insert, delete), substitute);
    }


    public int distanceTable(String s1, String s2) {
        opCount = 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                opCount++;
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                int insert = dp[i][j - 1] + 1;
                int delete = dp[i - 1][j] + 1;
                int substitute = dp[i - 1][j - 1] + cost;
                dp[i][j] = Math.min(Math.min(insert, delete), substitute);
            }
        }
        return dp[m][n];
    }


    public int distanceTwoRows(String s1, String s2) {
        opCount = 0;
        int m = s1.length();
        int n = s2.length();

        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                opCount++;
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                int insert = curr[j - 1] + 1;
                int delete = prev[j] + 1;
                int substitute = prev[j - 1] + cost;
                curr[j] = Math.min(Math.min(insert, delete), substitute);
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
}