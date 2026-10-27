package week11;

import java.util.*;
import java.io.*;
class PGS_배달 {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List <int []> [] graph = new ArrayList [N+1];

        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : road){
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            // 양방향 그래프
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});

        }
        int dist[] = new int [N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        //{노드번호, 가중치}
        // 가중치 순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b) -> Integer.compare(a[1], b[1]));

        // 초기화
        dist[1] = 0;
        pq.offer(new int[]{1, 0});

        // for (int i = 1; i <= N; i++) {
        //     System.out.println(i + " : " +
        // Arrays.deepToString(graph[i].toArray()));
        // }

        while(!pq.isEmpty()){
            int [] current = pq.poll();
            int currentNode = current[0];
            int currentCost = current[1];

            if(currentCost > dist[currentNode]){
                continue;
            }
            // 현재노드의 graph 탐색
            for(int[] edge : graph[currentNode]){
                int nextNode = edge[0];
                int nextCost = edge[1];

                int weight = currentCost + nextCost;

                if(weight < dist[nextNode]){
                    dist[nextNode] = weight;
                    pq.offer(new int[]{nextNode, weight});
                }


            }


        }
        for(int i = 1; i <= N; i++){
            if(dist[i] <= K){
                answer ++;
            }
        }

        return answer;
    }

}
