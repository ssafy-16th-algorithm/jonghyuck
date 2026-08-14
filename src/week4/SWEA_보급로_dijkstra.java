package week4;


import java.io.*;
import java.util.*;


class SWEA_보급로_dijkstra
{
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0 ,-1};
    static int [][] map;
    static int [][] costs;
    static int N;
    static int time;

    public static void main(String args[]) throws Exception
    {

        System.setIn(new FileInputStream("jonghyuck/src/week4/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            costs = new int[N][N];
            time = 0;
//           for(int row = 0; row < N; row ++){
//               StringTokenizer st = new StringTokenizer(br.readLine());
//               for(int col = 0; col < N; col++){
//                   map[row][col] = Integer.parseInt(st.nextToken());
//
//               }
//           }
            // 문자열로 받아서 쪼개고 형변환 해야함
            for(int row = 0; row < N; row++){
                String line = br.readLine();
                for(int col = 0; col < N; col++){
                    map[row][col] = line.charAt(col) - '0';
                }
            }
            StringBuilder sb = new StringBuilder();

            int answer = dijkstra(0, 0);

            sb.append('#').append(test_case).append(' ').append(answer);

            System.out.println(sb);

        }
    }
    static int dijkstra(int startRow, int startCol){
        PriorityQueue <int []> queue = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        queue.offer(new int[]{0, 0, 0});
        for(int i = 0; i < N; i++){
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        costs[startRow][startCol] = 0;

        while(!queue.isEmpty()){
            int [] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int currentCost = current[2];

            if(currentCost > costs[currentRow][currentCol]) {
                continue;
            }
            if(currentRow == N-1 && currentCol == N-1){
                return currentCost;
            }

            for(int direction = 0; direction < 4; direction ++){

                int nextRow = currentRow + dx[direction];
                int nextCol = currentCol + dy[direction];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N){
                    continue;
                }
                int nextCost = currentCost + map[nextRow][nextCol];

                if(nextCost < costs[nextRow][nextCol]){

                    costs[nextRow][nextCol] = nextCost;

                    queue.offer(new int [] {nextRow, nextCol, nextCost});


                }


            }


        }return costs[N-1][N-1];
    }
}
