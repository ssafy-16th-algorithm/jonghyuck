package week5;

import java.util.*;
import java.io.*;

class Solution
{
    static List<Integer> list;
//    static int [][] map;
//    static int [][] temp;
    static int maxValue;
    static int N;
    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        // 2차원 정적 배열, stack으로 풀려고 해봤는데 결국
        // remove했을 때 인덱스 땡겨지고 크기에 동적인 ArrayList가 최적
        // dfs+백트래킹

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());

            maxValue = Integer.MIN_VALUE;
//            map = new int [N][N];
//            temp = new int [N][N];
            StringTokenizer st = new StringTokenizer(br.readLine());
//            int [] arr = new int [N];
//            for(int i = 0; i < N; i++) {
//                arr[i] = Integer.parseInt(st.nextToken());
//            }
//
//            for(int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    map[i][j] = arr[j];
//                    temp[i][j] = arr[j];
//                }
//            }
//
//            maxValue = Integer.MIN_VALUE;


            list = new ArrayList<>();

            for(int i = 0; i < N; i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            findMax(0);

            StringBuilder sb = new StringBuilder();

            sb.append('#').append(test_case).append(' ').append(maxValue);

            System.out.println(sb);
        }
    }
    static public void findMax(int score){
        if(list.size() == 1){
            maxValue = Math.max(maxValue, score + list.get(0));
            return;
        }
        for(int i = 0; i < list.size(); i++){
            int currentScore;

            // 맨 왼쪽 블록 제거 할 대
            if(i == 0){
                currentScore = list.get(1);
            }
            // 맨 오른쪽 블록 제거 할 때
            else if(i == list.size() - 1){
                currentScore = list.get(i - 1);
            }
            // 가운데 블록 제거할 때
            else{
                currentScore = list.get(i - 1) * list.get(i + 1);
            }


            int removed = list.remove(i);

            findMax(score + currentScore);

            // list 복구
            list.add(i, removed);
        }


    }

}