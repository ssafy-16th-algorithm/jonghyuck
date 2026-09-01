package week6;

import java.util.*;
import java.io.*;

class Solution
{
    static int N;
    static int X;
    static int [][] map;
    static int [] row;
    static int [] col;
    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());


        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            X = Integer.parseInt(st.nextToken());

            map = new int [N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = 0;
            // 열과 행을 일차원배열로 쪼개고, 객체 생성 할때마다 검사.

            for(int i = 0; i < N ; i++){
                row = new int [N];
                col = new int [N];

                for(int j = 0; j < N; j++){
                    row[j] = map[i][j];
                    col[j] = map[j][i];

                }

                if(checkWay(row)){
                    answer ++;
                }
                if(checkWay(col)){
                    answer ++;
                }
            }




            StringBuilder sb = new StringBuilder();

            sb.append('#').append(test_case).append(' ').append(answer);

            System.out.println(sb);
        }
    }
    static public boolean checkWay(int [] way){
        // 동일한 셀에 경사로 두개 설치 할 수 없으므로
        boolean [] visited = new boolean[N];

        for(int i = 0; i < N - 1 ; i++){
            int diff = way[i] - way[i + 1];

            if(diff == 0 ){
                continue;
            }
            // 차이 2 이상이면 활주로 건설 불가
            if(Math.abs(diff) >= 2){
                return false;
            }
            // 올라가는 경사로 ex 2 2 3 3
            if(diff == -1){
                // 차이나는 칸부터 왼쪽방향으로 X만큼 검사
                for(int j = i; j > i - X; j--){
                    if(j < 0){
                        return false;
                    }
                    // 높이가 다를 때
                    if(way[j] != way[i]){
                        return false;
                    }
                    // 이미 경사로 존재할 때
                    if(visited[j]){
                        return false;
                    }
                }
                // 다 검사 성공하면 경사로 설치
                for(int j = i; j > i - X; j--){
                    visited[j] = true;

                }

            }
            // way[i] > way[i+1]
            // 내려가는 경사로 ex 3 3 2 2
            else if(diff == 1){
                // 다음 인덱스부터 오른쪽칸 X만큼 검사
                for(int j = i + 1; j <= i + X; j++){
                    if(j >= N){
                        return false;
                    }
                    // 높이가 다를 때
                    if(way[j] != way[i + 1]){
                        return false;
                    }
                    // 이미 경사로 존재할 때
                    if(visited[j]){
                        return false;
                    }
                }
                // 다 검사 성공하면 경사로 설치
                for(int j = i + 1; j <= i + X; j++){
                    visited[j] = true;

                }
            }



        }



        return true;
    }

}
