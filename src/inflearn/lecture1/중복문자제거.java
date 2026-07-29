package inflearn.lecture1;
import java.util.*;
import java.io.*;

// HashSet
public class 중복문자제거{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(char ch : str.toCharArray()){
            if(!set.contains(ch)){
                set.add(ch);
                sb.append(ch);
            }
        }




        System.out.println(sb);

    }



}
