/// //////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/// //////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/// //////////////////////////////////////////////////////////////////////////////////////////
package week1;

import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
// DFS
class SWEA_프로세서연결하기 {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static ArrayList<int[]> cores;
    static int maxConnect;
    static int minLength;


    public static void main(String args[]) throws Exception {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
        // Point : (1순위)<최대한 많은 Core에 전원을 연결한 상태>의 (2순위)<전선의 최소값>
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            // false = 비어 있는 칸
            // true = 코어 또는 설치된 전선이 있는 칸
            visited = new boolean[N][N];

            // 코어의 좌표를 저장할 list
            cores = new ArrayList<>();
            // 최대 연결 수
            maxConnect = 0;
            // 최소 길이
            minLength = Integer.MAX_VALUE;

            for (int row = 0; row < N; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                    // 가장자리 제외한 core 좌표 저장
                    if (map[row][col] == 1) {
                        // 코어가 있으면 지나갈 수 없다.
                        visited[row][col] = true;
                        if (0 < row && row < N - 1 && 0 < col && col < N - 1) {
                            cores.add(new int[]{row, col});
                        }
                    }
                }
            }
            dfs(0, 0, 0);
            sb.append("#").append(test_case).append(" ").append(minLength).append("\n");
        }
        System.out.print(sb);
    }


    // 코어의 연결방향 4방향 탐색 + 전선의 길이 최소가 되도록 => dfs
    // 탐색하고 전선을 제거해야 방해되지 않음 (원상 복구) => 백트래킹
    static void dfs(int count, int length, int connect) {

        // 모든 내부 코어를 처리한 경우
        if (count == cores.size()) {
            // (1순위)
            // 기존보다 더 많은 코어를 연결했다면
            if (connect > maxConnect) {
                maxConnect = connect;
                minLength = length;
            }
            // (2순위)
            // 연결된 코어 수가 같다면
            // 전선 길이가 더 짧은 값을 선택
            else if (connect == maxConnect) {
                minLength = Math.min(minLength, length);
            }

            return;
        }

        // 현재 코어의 좌표
        int[] currentCore = cores.get(count);
        int row = currentCore[0];
        int col = currentCore[1];

        // 4방향 연결
        for (int direction = 0; direction < 4; direction++) {

            int currentLength = connectWire(row, col, direction);

            // 해당 방향으로 연결할 수 없는 경우
            if (currentLength == 0) {
                continue;
            }

            // 현재 방향으로 전선이 설치된 상태에서 다음 코어 탐색(현재 코어를 연결한 경우)
            dfs(count + 1, length + currentLength, connect + 1);

            // 다른 방향을 탐색하기 위해 현재 설치한 전선을 제거
            removeWire(row, col, direction);
        }
        // 미연결 경우는 마지막에 한 번만 탐색
        dfs(count + 1, length, connect);
    }

    // 해당 방향으로 전선을 설치 / 성공하면 설치한 전선 길이를 반환하고, 중간에 코어나 기존 전선을 만나면 0을 반환
    static int connectWire(int row, int col, int direction) {
        int length = 0;
        int nextRow = row + dx[direction];
        int nextCol = col + dy[direction];

        // 우선 실제 visited 배열에 표시하면서 이동. 만약 중간에 막히면 지금까지 표시한 전선을 다시 false로 복구
        Queue<int[]> wirePositions = new ArrayDeque<>();
        while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
            // 코어나 기존 전선을 만난 경우
            if (visited[nextRow][nextCol]) {
                // 임시로 설치한 전선 제거
                while (!wirePositions.isEmpty()) {
                    int[] position = wirePositions.poll();
                    int wireRow = position[0];
                    int wireCol = position[1];

                    visited[wireRow][wireCol] = false;
                }
                return 0;
            }

            // 현재 칸에 전선 설치
            visited[nextRow][nextCol] = true;
            wirePositions.offer(new int[]{nextRow, nextCol});
            length++;

            nextRow += dx[direction];
            nextCol += dy[direction];
        }

        return length;
    }

    // dfs 끝나고 전선 제거
    static void removeWire(int row, int col, int direction) {
        int nextRow = row + dx[direction];
        int nextCol = col + dy[direction];

        while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
            visited[nextRow][nextCol] = false;
            nextRow += dx[direction];
            nextCol += dy[direction];
        }
    }
}