package inflearn.lecture1;

import java.io.*;

public class 문자열압축
{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char [] charArray = str.toCharArray();

        String answer = "";

        for (int i = 0; i < charArray.length ; i++){
            if(Character.isDigit(charArray[i])){
                answer += charArray[i];
            }
        }





        System.out.println(Integer.parseInt(String.valueOf(answer)));

    }



}
