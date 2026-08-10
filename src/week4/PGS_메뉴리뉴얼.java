package week4;

import java.util.*;
import java.io.*;

class Solution {
    static Map <String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {


        for(String order : orders){
            char [] charArray = order.toCharArray();
            Arrays.sort(charArray);
            for(int r : course){
                combinations(charArray, 0, charArray.length, r, "");
            }

        }
        // map 순회?
        System.out.println(map);
        String[] answer = {};

        return answer;
    }
    public void combinations(char[] arr, int start, int n, int r, String result){
        if(r == 0){
            if (map.containsKey(result)) {
                map.put(result, map.get(result) + 1);
            } else {
                map.put(result, 1);
            }
            return;
        }else{
            for(int i = start; i < n; i++){
                combinations(arr, i + 1, n, r - 1, result + arr[i]);

            }
        }

    }
}
