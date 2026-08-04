package week2;
/*
Union-Find를 써야하는 문제 판별 :
두 그룹을 합쳐야 하는 문제
1) 사이클 발생 여부 확인하는 문제
2) 같은 집합인지 확인하는 문제
3) 간선 하나씩 추가
4) 모든 노드를 최소 비용으로 연결
5) 최소 신장 트리
6) 간선 비용의 합을 최소화

1. 합치기: union(a, b)
2. 같은 그룹인지 확인: find(a) == find(b)
1번과 2번을 연결한다.
2번과 3번을 연결한다.
1번과 3번이 연결되어 있는지 확인한다.
*/
class Solution {
    // 각 노드의 부모를 저장하는 배열
    int[] parent;
    public int[] findRedundantConnection(int[][] edges) {

        // 노드 번호가 1부터 시작하므로 길이를 edges.length + 1로 설정
        parent = new int[edges.length + 1];

        // 자기 자신을 부모로 설정
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        // 간선을 입력 순서대로 확인
        for (int[] edge : edges) {

            int a = edge[0];
            int b = edge[1];

            // 이미 같은 집합이면 이 간선을 추가할 때 사이클 발생
            if (find(a) == find(b)) {
                return edge;
            }

            // 아직 다른 집합이라면 연결
            union(a, b);
        }

        return new int[0];
    }

    // 해당 노드가 속한 집합의 대표 부모 찾기
    public int find(int node) {

        // 자기 자신이 부모라면 대표 노드
        if (parent[node] == node) {
            return node;
        }

        // 부모를 따라가면서 대표 부모 찾기
        parent[node] = find(parent[node]);

        return parent[node];
    }

    // 두 노드가 속한 집합 합치기
    public void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        // b의 대표 부모를 a의 대표 부모 밑으로 연결
        parent[rootB] = rootA;
    }
}
