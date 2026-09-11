package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_보급로 {

    // 가중치가 작은 순으로 정렬하고, 객체 생성을 편하게 하기 위한 클래스 선언
    static class Node implements Comparable<Node>{
        int r;
        int c;
        int cost;

        Node(int r, int c, int cost){
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N;
    static int map [][];
    static int costs [][];
    static int [] dx = {0, 0, -1, 1};
    static int [] dy = {-1, 1, 0, 0};
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int test_case = 1 ; test_case <= T; test_case++){

            N = Integer.parseInt(br.readLine());

            map = new int [N][N];
            costs = new int[N][N];

            for(int row = 0; row < N; row ++){

                String line = br.readLine();

                for(int col = 0; col < N; col ++){
                    map[row][col] = line.charAt(col) - '0';
                }
            }


            int answer = dijkstra(0, 0);


            sb.append('#').append(test_case).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);


    }

    static public int dijkstra(int startRow, int startCol){
        // compareTo를 Node클래스에서 Override했으므로, 알아서 cost순으로 오름차순 정렬된다.
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0,0,0));

        for(int i = 0; i < N; i++){
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }

        costs[startRow][startCol] = 0;

        while (!pq.isEmpty()){
            Node current = pq.poll();
            int currentRow = current.r;
            int currentCol = current.c;
            int currentCost = current.cost;

            //112 ms -> 110ms
            //실행시간
            //실행시간은 비슷..

            // 불필요한 탐색을 줄일 수 있다.
            if(currentCost > costs[currentRow][currentCol]){
                continue;
            }

            if(currentRow == N - 1 && currentCol == N - 1){
                return currentCost;
            }

            for(int dir = 0; dir < 4; dir++){
                int nextRow = currentRow + dx[dir];
                int nextCol = currentCol + dy[dir];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N){
                    continue;
                }
                // 다음 가중치에 현재 가중치 + map의 가중치
                int nextCosts = currentCost + map[nextRow][nextCol];
                if(nextCosts < costs[nextRow][nextCol]){

                    costs[nextRow][nextCol] = nextCosts;

                    pq.offer(new Node(nextRow, nextCol, nextCosts));
                }


            }


        }
        return costs[N - 1][N - 1];
    }
}
