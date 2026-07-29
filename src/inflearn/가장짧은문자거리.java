package inflearn;
import java.util.*;
import java.io.*;

public class 가장짧은문자거리{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        char t = st.nextToken().charAt(0);
        String [] answer = new String[s.length()];

        ArrayList<Integer> indexList = new ArrayList<Integer>();
        ArrayList<Integer> answerList = new ArrayList<Integer>();

        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == t){
                indexList.add(i);
            }
        }
        int tempDistance = 0;
        for(int i = 0; i < s.length();i++){
            int minDistance = Integer.MAX_VALUE;

            for(int j = 0; j < indexList.size() ; j++){
                tempDistance = Math.abs(i - indexList.get(j));
                if (tempDistance <= minDistance){
                    minDistance = tempDistance;

                }
            }
            answerList.add(minDistance);
        }
//        for(int i = 0 ; i < answerList.size() ; i++){
//            System.out.print(answerList.get(i));
//
//        }
        for(int i = 0; i < answerList.size() ; i++){
            if(i>0){
                System.out.print(" ");
            }
            System.out.print(answerList.get(i));
        }
    }



}
