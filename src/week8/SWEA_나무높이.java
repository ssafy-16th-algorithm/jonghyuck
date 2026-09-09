package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA_나무높이 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T ; test_case++){

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int [] trees = new int [N];
            int [] diffs = new int [N];

            int maxTree = 0;
            for(int i = 0; i < N; i++){
                trees[i] = Integer.parseInt(st.nextToken());
                // 높이 최대인 나무를 구한다
                maxTree = Math.max(maxTree, trees[i]);
            }

            int oneCount = 0;
            int twoCount = 0;
            for(int i = 0; i < N; i++){
                // 각 나무 높이 차
                diffs[i] = maxTree - trees[i];
                // 1과 2가 필요한 개수를 구한다
                oneCount += diffs[i] % 2;
                twoCount += diffs[i] / 2;
            }
            // 만약 twoCount와 oneCount의 차가 2보다 크다면
            // twoCount를 쪼개서 oneCount 두개로 분배한다.
            while(twoCount - oneCount >= 2){
                twoCount --;
                oneCount += 2;
            }

            int days = 0;

            // 만약 oneCount가 twoCount보다 많다면, 첫번째에서 물 주고 끝나므로 1을 뺀다
            if(oneCount > twoCount){
                days = oneCount * 2 - 1;
            }else{
                days = twoCount * 2;
            }




            StringBuilder sb  = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(days);
            System.out.println(sb);
        }

    }
}
