package week6;

import java.util.*;
import java.io.*;

class PGS_여행경로 {
    static boolean visited[];
    static String [][] tickets;
    static String [] answer;
    public String[] solution(String [][] tickets) {
        this.tickets = tickets;
        // Arrays.sort(tickets, (o1, o2) -> o1[0].compareTo(o2[0]) );
        // 1순위: 출발 공항
        // 2순위: 도착 공항
        Arrays.sort(tickets, (o1, o2) -> {
            if (o1[0].equals(o2[0])) {
                return o1[1].compareTo(o2[1]);
            }
            return o1[0].compareTo(o2[0]);
        });
        answer = new String [tickets.length + 1];
        visited = new boolean [tickets.length];

        dfs("ICN", 0);
        return answer;
    }
    // dfs + 백트래킹 -> 갈수 있는 경로 구한 후 초기화
    // current : 현재 경로(시작은 ICN), depth : tickets의 길이
    // void로 풀려면 flag를 설정하기
    // boolean반환하는 함수로
    // true  = 이 경로로 가니까 정답 찾았음
    // false = 이 경로로 갔는데 정답 못 찾았음
    public boolean dfs(String current, int depth){
        // ICN출발
        answer[depth] = current;
        if(depth == tickets.length){
            // 그냥 return으로 끝내면 dfs가 계속 돈다.
            return true;
        }
        for(int i = 0; i < tickets.length; i++){
            if(!visited[i] && tickets[i][0].equals(current)){

                answer[depth] = current;
                visited[i] = true;

                boolean result = dfs(tickets[i][1], depth + 1);

                if(result){
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;


    }
}

