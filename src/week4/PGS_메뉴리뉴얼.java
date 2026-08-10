package week4;

import java.util.*;
import java.io.*;

class Solution {
    static Map <String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {


        // String배열을 char배열로 변환하고, 조합을 map에 추가한다.
        for(String order : orders){
            char [] charArray = order.toCharArray();
            Arrays.sort(charArray);
            for(int r : course){
                combinations(charArray, 0, charArray.length, r, "");
            }

        }

//       System.out.println(map);
//        String[] answer = new String [course.length];

        // course 하나당 여러개 menu가 올수 있으므로 List 선언
        List <String> answerList = new ArrayList<>();
        // map 순회
        // 가장많이, 최소 2명 이상
        // key를 저장하는 set
        Set<String> keySet = map.keySet();

        // 각 course의 길이마다, 최대로 많이 나온 빈도수를 구한다.
        for(int i = 0; i < course.length ; i++){
            // 빈도수 최댓값
            int maxValue = 0;
            for(String key : keySet){
                String menu = key;
                int count = map.get(key);
                if(menu.length() == course[i] && count >= 2 && count > maxValue){
                    maxValue = count;
                }

            }
            // 빈도수가 가장 많고, 2명 이상 고른 메뉴를 answerList에 add
            for(String key : keySet){
                String menu = key;
                int count = map.get(key);
                if(menu.length() == course[i] && count == maxValue && count >=2){
                    answerList.add(menu);
                }
            }

        }
        // 오름차순 정렬
        Collections.sort(answerList);

        // return타입이 String[]이므로, String 배열로 변환
        return answerList.toArray(new String[answerList.size()]);
    }

    // 메뉴의 조합을 map에 추가하는 함수
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
