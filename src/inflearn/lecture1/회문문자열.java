package inflearn.lecture1;
import java.io.*;

public class 회문문자열{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char [] charArray = str.toCharArray();
        char [] answer = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++){
            answer[i] += Character.toUpperCase(charArray[i]);
        }
        String result = "";
        int left = 0;
        int right = answer.length - 1;
        while(left < right){
           if(answer[left] == answer[right]){
               result = "YES";
               left++;
               right--;
            }
           else{
               result = "NO";
               break;
           }
        }





        System.out.println(result);

    }



}
