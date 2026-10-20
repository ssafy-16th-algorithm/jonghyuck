package week10;

import java.util.*;

class Solution {
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    static int[][][] minCost;
    static int answer;
    static int N;

    public int solution(int[][] board) {
        N = board.length;

        answer = Integer.MAX_VALUE;

        // 위치와 진입 방향별 최소 비용
        minCost = new int[N][N][4];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(minCost[i][j], Integer.MAX_VALUE);
            }
        }

        dfs(board, 0, 0, 0, 0, -1);

        return answer;
    }

    public void dfs(int[][] board, int row, int col,
                    int straightCnt, int cornerCnt, int dir) {

        int cost = straightCnt * 100 + cornerCnt * 500;

        // 현재 비용이 정답 이상이면 종료
        if (cost >= answer) {
            return;
        }

        // 목적지 도착
        if (row == N - 1 && col == N - 1) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (nextRow < 0 || nextRow >= N ||
                    nextCol < 0 || nextCol >= N) {
                continue;
            }

            if (board[nextRow][nextCol] == 1) {
                continue;
            }

            // 다음 경로의 코너 개수
            int nextCornerCnt = cornerCnt;

            if (dir != -1 && dir != i) {
                nextCornerCnt++;
            }

            // 다음 위치까지의 누적 비용
            int nextCost = (straightCnt + 1) * 100 + nextCornerCnt * 500;

            // 이미 더 저렴하게 방문했다면 생략
            if (nextCost >= minCost[nextRow][nextCol][i]) {
                continue;
            }

            // 최소 비용 갱신
            minCost[nextRow][nextCol][i] = nextCost;

            dfs(board, nextRow, nextCol, straightCnt + 1, nextCornerCnt, i);
        }
    }
}

