package inflearn;
import java.util.*;
import java.io.*;

// toCharArray 활용
public class 특정문자뒤집기{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        char [] answer = str.toCharArray();

        char temp = ' ';
        int left = 0;
        int right = str.length()-1;

        while(left < right){

            if(!Character.isLetter(str.charAt(left))){
                left ++;
            }
            else if(!Character.isLetter(str.charAt(right))){
                right --;
            }
            else{
                temp = answer[left];
                answer[left] = answer[right];
                answer[right] = temp;

                left ++;
                right --;
            }
        }

        System.out.println(String.valueOf(answer));

    }


}
