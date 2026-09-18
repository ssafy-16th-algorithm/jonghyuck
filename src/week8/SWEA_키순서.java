package week8;

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
