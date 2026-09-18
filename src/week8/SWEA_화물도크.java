package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_화물도크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1 ; test_case <= T; test_case++){

            int N = Integer.parseInt(br.readLine());

            PriorityQueue <int[]> pq = new PriorityQueue<>(
                    (a,b) -> (a[1] - b[1]));

            for(int i = 0; i < N ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});




            }

            int answer = 0;
            int endTime = Integer.MIN_VALUE;
            while (!pq.isEmpty()){
                int current [] = pq.poll();
                int currentStart = current[0];
                int currentEnd = current[1];

                if(endTime <= currentStart){
                    endTime = currentEnd;
                    answer ++;
                }


            }
            sb.append('#').append(test_case).append(' ').append(answer).append('\n');




        }
        System.out.println(sb);
    }
}
