package week5;

import java.util.*;
import java.io.*;

class SWEA_벽돌깨기
{
    static int N;
    static int W;
    static int H;
    static int map[][];
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



        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int [W][H];
            for(int i = 0; i < W; i ++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < H; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }



        }
    }
    static public void breakBlock(){


    }

}