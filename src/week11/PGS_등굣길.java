package week11;

import java.util.*;
import java.io.*;


class Solution {
    public int solution(int m, int n, int[][] puddles) {

        int NUM = 1_000_000_007;

        int[][] dp = new int[n][m];
        boolean[][] puddlesPoint = new boolean[n][m];

        // 물웅덩이 위치
        for (int[] puddle : puddles) {
            int col = puddle[0] - 1;
            int row = puddle[1] - 1;
            puddlesPoint[row][col] = true;
        }
        dp[0][0] = 1;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (puddlesPoint[row][col]) {
                    continue;
                }
                // 위쪽에서 내려오는 경우
                if (row > 0) {
                    dp[row][col] += dp[row - 1][col];
                }
                // 왼쪽에서 오는 경우
                if (col > 0) {
                    dp[row][col] += dp[row][col - 1];
                }
                dp[row][col] %= NUM;
            }
        }

        return dp[n - 1][m - 1];
    }
}

