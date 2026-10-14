package week5;

import java.util.*;
import java.io.*;

class SWEA_벽돌깨기 {

    static int N, W, H;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = dfs(0);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    // 1. N번 구슬을 발사할 열 선택
    static int dfs(int count) {

        int remain = countBlocks();
        // 종료조건
        if (count == N || remain == 0) {
            return remain;
        }

        int answer = Integer.MAX_VALUE;
        for (int col = 0; col < W; col++) {

            // 해당 열의 가장 위쪽 벽돌 찾기
            int row = 0;

            while (row < H && map[row][col] == 0) {
                row++;
            }
            // 해당 열에 벽돌이 없다면 건너뛰기
            if (row == H) {
                continue;
            }
            // 배열 temp
            int[][] temp = copyMap();
            // 벽돌 깨기
            breakBlock(row, col);
            // 벽돌 중력 적용하기
            move();
            answer = Math.min(answer, dfs(count + 1));
            // 배열 복원 backtracking
            map = temp;

        }
        return answer;
    }

    // 2. BFS->연쇄 폭발
    static void breakBlock(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        int startBoom = map[row][col];
        queue.offer(new int[]{row, col, startBoom});
        map[row][col] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentRow = current[0];
            int currentCol = current[1];
            int boom = current[2];
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < boom; j++) {
                    int nextRow = currentRow + dx[i] * j;
                    int nextCol = currentCol + dy[i] * j;

                    if (nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W) {
                        break;
                    }

                    if (map[nextRow][nextCol] == 0) {
                        continue;
                    }
                    int nextBoom = map[nextRow][nextCol];
                    map[nextRow][nextCol] = 0;
                    queue.offer(new int[]{
                            nextRow, nextCol, nextBoom
                    });
                }
            }
        }
    }

    // 3. 중력 적용
    static void move() {
        for (int col = 0; col < W; col++) {

            int bottom = H - 1;
            for (int row = H - 1; row >= 0; row--) {
                if (map[row][col] != 0) {
                    map[bottom][col] = map[row][col];
                    bottom--;
                }
            }
            for (int row = bottom; row >= 0; row--) {
                map[row][col] = 0;
            }
        }
    }

    // 4. 배열 복사
    static int[][] copyMap() {

        int[][] copy = new int[H][W];

        for (int i = 0; i < H; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    // 5. 남은 벽돌 개수
    static int countBlocks() {
        int remain = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {

                if (map[i][j] != 0) {
                    remain++;
                }
            }
        }

        return remain;
    }
}
