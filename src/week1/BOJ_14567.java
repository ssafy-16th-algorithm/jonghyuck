package week1;
import java.util.*;
import java.io.*;

// 선수과목
// 위상정렬
public class BOJ_14567 {
    static int N;
    static int M;

    // graph[i] : i를 선수과목으로 요구하는 과목들 저장
    static ArrayList<Integer>[] graph;
    // 각 과목에 필요한 선수과목 개수
    static int[] indegree;
    // 학기
    static int[] semester;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        indegree = new int[N+1];
        semester = new int[N+1];

        // subject별 그래프 객체 생성
        for (int subject = 1; subject <= N; subject++){
            graph[subject] = new ArrayList<>();
        }

        // graph, indegree에 input 넣기
        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            graph[before].add(after);

            indegree[after]++;

        }
        /*
        graph[subject] 형태
         1 : [2,3]
         2 : [5]
         3 : []
         4 : [5]
         5 : []

        indegree[after] 형태(진입 차수)
        indegree[1] = 0
        indegree[2] = 1
        indegree[3] = 1
        indegree[4] = 0
        indegree[5] = 2
        indegree[6] = 0
         */

        sort();

        for(int subject = 1; subject <= N; subject++){
            sb.append(semester[subject]).append(' ');
        }

        System.out.println(sb);
    }
    // 위상정렬 함수
    static void sort(){
        Queue<Integer> queue = new ArrayDeque<>();

        // 선수과목 없는(indegree) 과목 큐에 삽입
        for(int subject = 1; subject <= N; subject++){
            if(indegree[subject] == 0){
                queue.offer(subject);
                semester[subject] = 1;
            }
        }
        while(!queue.isEmpty()){
            int current = queue.poll();

            for(int next : graph[current]){
                indegree[next] --;

                // 더 늦은 선수과목을 기준으로
                semester[next] = Math.max(semester[next],semester[current] + 1);

                if(indegree[next] == 0){
                    queue.offer(next);
                }

            }
        }


    }
}
