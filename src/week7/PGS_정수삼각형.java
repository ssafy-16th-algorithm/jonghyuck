package week7;

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] triangle) {
        // 이동은 해당인덱스와 다음 인덱스(idx, idx+1)
        int height = triangle.length;
        // dp 테이블
        int dp [][] = new int [height + 1][height + 1];

        dp[0][0] = triangle[0][0];

        // dp 테이블에 경우의수 누적
        for(int i = 1; i < height; i++){

            for(int j = 0; j < triangle[i].length; j++){

                if(j == 0){
                    dp[i][j] = triangle[i][j] + dp[i-1][j];
                }

                else if(j == (triangle[i].length - 1)){
                    dp[i][j] = triangle[i][j] + dp[i-1][j-1];
                }

                else{
                    dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
                }

            }
        }

//         for(int i = 0; i < height - 1; i++){

//             if(triangle[i+1][idx] > triangle[i+1][idx+1]){
//                 dp[i + 1] = dp[i] + triangle[i+1][idx];
//             }else if(triangle[i+1][idx] <= triangle[i+1][idx+1]){
//                 dp[i + 1] = dp[i] + triangle[i+1][idx+1];
//                 idx += 1;
//             }

//         }
        int answer = 0;

        // dp 테이블 마지막 행 중 최대
        for(int i = 0; i < triangle[height-1].length; i++){

            if(answer < dp[height-1][i]){
                answer = dp[height-1][i];
            }
        }

        return answer;
    }
}
