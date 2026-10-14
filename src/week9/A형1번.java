package week9;

import java.io.*;
import java.util.*;

public class A형1번 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1 ; test_case <= T; test_case++){

            int N = Integer.parseInt(br.readLine());

            int[][] houses = new int[N][3];

            boolean[][] isHouse = new boolean[31][31];

            ArrayList<int[]> stations = new ArrayList<>();


            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                //x
                houses[i][0] = Integer.parseInt(st.nextToken());
                //y
                houses[i][1] = Integer.parseInt(st.nextToken());
                //d
                houses[i][2] = Integer.parseInt(st.nextToken());
                // 집 위치 표시
                isHouse[houses[i][0] + 15][houses[i][1] + 15] = true;
            }

            // 집과 집사이의 거리를 구해서 d의 합보다 작다면? 충전소 설치 가능 but 너무 복잡해짐
//            for(int i = 0; i < N; i++){
//                for(int j = 0; j < N; j++){
//                    if(Math.abs(houses[i][0] - houses[j][0]) + Math.abs(houses[i][1] - houses[j][1]) <= houses[i][2] + houses[j][2]){
//
//                    }
//                }
//
//            }


            // 31 x 31좌표에 모든 충전소를 놓는다 치고, 이용가능한지 완탐을 하는게 나을수도. O((961) x N)


            for (int x = -15; x <= 15; x++) {

                for (int y = -15; y <= 15; y++) {

                    // 집이 있는 위치에는 충전소 설치 불가능
                    if (isHouse[x + 15][y + 15]) {
                        continue;
                    }

                    stations.add(new int[]{x, y});
                }
            }
            // 집과 충전소 거리 후보
            // dist[i][j] = i번 집과 j번 충전소 후보 사이의 거리
            int[][] dist = new int[N][stations.size()];

            for (int i = 0; i < N; i++) {

                for (int j = 0; j < stations.size(); j++) {

                    int houseX = houses[i][0];
                    int houseY = houses[i][1];

                    int stationX = stations.get(j)[0];
                    int stationY = stations.get(j)[1];

                    dist[i][j] = Math.abs(houseX - stationX) + Math.abs(houseY - stationY);
                }
            }

            int answer = Integer.MAX_VALUE;
            boolean canOne = false;

            for (int station = 0; station < stations.size(); station++) {

                boolean possible = true;
                int sum = 0;

                for (int house = 0; house < N; house++) {

                    int distance = dist[house][station];
                    // 해당 집의 최대 이용 거리보다 멀면 해당 충전소 하나로는 커버 x
                    if (distance > houses[house][2]) {
                        possible = false;
                        break;
                    }

                    sum += distance;
                }

                if (possible) {
                    canOne = true;
                    answer = Math.min(answer, sum);
                }
            }

            // 충전소 1개로는 부족할 때
            if (!canOne) {

                for (int i = 0; i < stations.size(); i++) {

                    for (int j = i + 1; j < stations.size(); j++) {

                        boolean possible = true;
                        int sum = 0;

                        for (int house = 0; house < N; house++) {

                            int d1 = dist[house][i];
                            int d2 = dist[house][j];

                            int limit = houses[house][2];

                            boolean canUse1 = d1 <= limit;
                            boolean canUse2 = d2 <= limit;

                            // 두 충전소 모두 이용 불가능
                            if (!canUse1 && !canUse2) {
                                possible = false;
                                break;
                            }

                            // 둘 다 가능하면 더 가까운 충전소 사용
                            if (canUse1 && canUse2) {
                                sum += Math.min(d1, d2);
                            }

                            // 1번 충전소만 가능
                            else if (canUse1) {
                                sum += d1;
                            }
                            // 2번 충전소만 가능
                            else {
                                sum += d2;
                            }
                        }
                        if (possible) {
                            answer = Math.min(answer, sum);
                        }
                    }
                }
            }


            // 충전소 2개로도 안될때
            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }


            sb.append('#').append(test_case).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}