// 최소 신장 트리
// 크루스칼 알고리즘
//(1) 주어진 그래프의 모든 간선에 대해서, 간선의 연결비용을 낮은 순으로 오름 차순 정렬한다.
//(2) 정렬된 간선 순서대로 선택하면서, 간선의 양 끝 정점을 Union 한다. 단, 이때 선택된 두 정점이 같은 집합에 속해있다면 사이클(cycle)이 있다고 판단하고 포함시키지 않는다.


import java.util.*;
import java.io.*;
public class PGS_섬연결하기 {

    // 엣지
    static int E;
    // 부모노드
    static int [] parent;

    public int solution(int n, int[][] costs) {
        // n은 정점의 개수(Vertex)

        // 간선의 개수(Edges)
        E = costs.length;

        parent = new int[n];
        int answer = 0;
        int bridgeCount =0;

        // 오름차순 정렬
        Arrays.sort(costs, (a,b) -> Integer.compare(a[2], b[2]));

        // 부모 노드 집합 생성
        for(int i = 0 ; i < n ; i++){
            parent[i] = i;
        }
        // 낮은 cost부터 크루스칼 알고리즘
        for (int[] cost : costs){

            int islandA = cost[0];
            int islandB = cost[1];
            int bridgeCost = cost[2];

            if(union(islandA, islandB)){
                answer += bridgeCost;
                bridgeCount ++;

            }

            if(bridgeCount == n-1){
                break;
            }



        }


        return answer;
    }
    // 두 그룹을 합친다.
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        // 같은 그룹이라면 사이클 생김. => 추가하지않음
        if(rootA == rootB){
            return false;
        }
        if (rootA > rootB){
            parent[rootB] = rootA;
        } else{
            parent[rootA] = rootB;
        }

        return true;
    }
    // 부모가 같은지 재귀로 찾는다.
    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        else{
            return find(parent[x]);
        }
    }
}