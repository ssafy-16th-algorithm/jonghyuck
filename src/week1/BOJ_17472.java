package week1;
import java.util.*;
import java.io.*;

// 다리만들기 2
// N x M 행렬
// 0 바다, 1 땅
// 모든 섬 연결하는 다리의 최솟값 출력. 모든 섬 연결 불가하면 -1
public class BOJ_17472 {
                    //   위 오 아래 왼
    static int [] dr = {-1, 0, 1, 0};
    static int [] dc = {0, 1, 0, -1};
    static int [][] map;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // think1 : 섬의 가장자리에서, 방향을 바꾸지 않고 탐색 -> 다른섬을 만난다면 해당 거리가 다리의 길이다.
        // 섬을 구분 -> 섬에 번호를 붙인다. (DFS)

        // 재귀를 위한 초기값 2 => 섬은 다른 섬일떄마다 2,3,4.. 넘버링
        int islandNum = 2;
        for (int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(map[row][col] == 1){
                    numberingIsland(row, col, islandNum);
                    islandNum ++;
                }
            }
        }

        // think 2 : 일단 섬과 섬사이의 최단거리를 구한다? => BFS (제약 : 2이상)
        // 근데 이제 직선으로 뻗어야하는데..
        // 섬이 다른걸 구분


    }
    

    // 섬을 구분하기위한 넘버링 DFS

    static void numberingIsland(int row, int col, int islandNum){

        map[row][col] = islandNum;

        for(int i = 0; i < 4 ; i++){
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];


            // map 밖
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M){
                continue;
            }

            // 섬 아니라면
            if(map[nextRow][nextCol] != 1){
                continue;
            }

            numberingIsland(nextRow, nextCol, islandNum);
        }

    }

    // 만들 수 있는 다리를 탐색하는 함수
    static void makeBridge(int row, int col){
        int startIsland = map[row][col];

        int bridgeLength = 0;
        while(true){
            for(int i = 0; i < 4; i++){
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    return;
                }
                // 바다라면
                if(map[nextRow][nextCol] == 0){
                    bridgeLength ++;

                }


            }
        }

    }

}
