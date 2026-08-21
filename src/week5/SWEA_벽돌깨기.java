package week5;

import java.util.*;
import java.io.*;

class SWEA_벽돌깨기
{
    static int N;
    static int W;
    static int H;
    static int map[][];
    static boolean visited [][];
    // 상하좌우
    static int dx [] = {0, 0, -1, 1};
    static int dy [] = {-1, 1, 0, 0};
    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/



        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int [H][W];
            for(int i = 0; i < H; i ++){

                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }



        }
    }
    // N이 작다 => 기본적으로 DFS
    // 폭발은 BFS?

    // 블록 깨는 함수
    static public void breakBlock(int row, int col){
        Deque<int[]> queue = new ArrayDeque<>();

        int startBoom = map[row][col];

        queue.offer(new int[] {row, col, startBoom});

        map[row][col] = 0;
        while(!queue.isEmpty()){
            int current [] = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int boom = current[2];

            for(int i = 0 ; i < 4; i ++){
                for(int j = 1; j < boom; j++){
                    int nextRow = currentRow + dx[i] * j;
                    int nextCol = currentCol + dy[i] * j;

                    if(nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W){
                        break;
                    }
                    if(map[nextRow][nextCol] == 0){
                        continue;
                    }
                    int nextBoom = map[nextRow][nextCol];

                    map[nextRow][nextCol] = 0;

                    queue.offer(new int[] {nextRow, nextCol, nextBoom});
                }


            }
        }


    }

    static int dfs(int count){
        int remain = 0;
        if(count == N){
            // remain
            for(int i = 0 ; i < H; i++){
                for(int j = 0 ; j < W; j++){
                    if(map[H][W] != 0){
                       remain ++;
                    }
                }
            }
            return remain;
        }
        for(int col = 0; col < W; col++){

            dfs(count + 1);
        }
        return 0;
    }

    static void move(){

    }

}