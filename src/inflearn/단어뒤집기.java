package inflearn;
import java.util.*;
import java.io.*;

public class 단어뒤집기{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N ; i++){
            String words = br.readLine();
            String answer = "";

            for(int j = words.length() - 1 ; j >= 0 ; j --){
                answer += words.charAt(j);
            }

            System.out.println(answer);
        }


    }



}
