package week4;

import java.util.*;
import java.io.*;


class SWEA_키순서
{
    static List<Integer>[] graph;
    static List<Integer>[] reverseGraph;
    static boolean[] visited;
    static int N;
    static int M;
    static int count;
    public static void main(String args[]) throws Exception
    {
        // 해당 학생보다 작은 친구 + 큰친구 = N-1이면 해당 학생은 순서를 아는것
        //

        System.setIn(new FileInputStream("jonghyuck/src/week4/sample_input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new ArrayList [N+1];
            reverseGraph = new ArrayList[N+1];



            for(int i = 1; i <= N; i++){
                graph[i] = new ArrayList<>();
                reverseGraph[i] = new ArrayList<>();
            }

            int answer = 0;

            for(int i = 0; i < M; i++ ){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                reverseGraph[b].add(a);

            }

            for(int i = 1; i <= N ; i++){
                visited = new boolean[N + 1];
                count = 0;
                dfs(i, graph);
                int taller = count;

                visited = new boolean[N + 1];
                count = 0;
                dfs(i, reverseGraph);
                int shorter = count;

                if(taller + shorter == N - 1){
                    answer ++;
                }
            }
            StringBuilder sb = new StringBuilder();


            sb.append('#').append(test_case).append(' ').append(answer);

            System.out.println(sb);


        }
    }

    static void dfs(int current, List<Integer>[] graph){
        visited[current] = true;
        for(int next : graph[current]){

            if(!visited[next]){
                visited[next] = true;
                count ++;


                dfs(next, graph);

            }
        }

    }
}