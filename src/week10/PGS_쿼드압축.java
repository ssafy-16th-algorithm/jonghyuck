package week10;

class PGS_쿼드압축 {

    static int zeroCnt;
    static int oneCnt;
    // static boolean visited[][];

    public int[] solution(int[][] arr) {
        int[] answer = {};
        int row = arr.length;
        int col = row;
        int n = 0;
        int size = row;
        while(size > 1){
            size = size / 2;
            n++;
        }

        // visited = new boolean [row][col];
        zeroCnt = 0;
        oneCnt = 0;
        quadTree(arr, 0, 0, n);
        return new int[]{zeroCnt, oneCnt};
    }
    public static void quadTree(int[][] arr, int row, int col, int n){
        int size = (int)Math.pow(2,n);


        // 영역의 합이 0 => 모든 원소가 0 , 압축
        // 영역의 합이 i*j => 모든 원소가 1, 압축
        int sum = 0;
        for(int i = row; i < row + size; i ++){
            for(int j = col; j < col + size; j ++){
                sum += arr[i][j];
            }
        }
        // 쿼드 압축 0으로
        if(sum == 0){
            zeroCnt ++;
            return;
        }
        // 쿼드 압축 1로
        else if(sum == size*size){
            oneCnt ++;
            return;
        }else{
            int half = size / 2;

            quadTree(arr, row, col, n - 1); // 2사분면
            quadTree(arr, row + half, col, n-1); // 3사분면
            quadTree(arr, row, col + half, n-1); // 1사분면
            quadTree(arr, row + half, col + half, n-1); // 4사분면
        }



    }

}
