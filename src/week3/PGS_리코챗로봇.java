package week3;

import java.util.ArrayDeque;
import java.util.Queue;

class PGS_리코챗로봇 {
    static int dx [] = {-1, 0, 1, 0};
    static int dy [] = {0, 1, 0 ,-1};
    public int solution(String[] board) {
        int startRow = 0;
        int startCol = 0;


        for(int i = 0; i < board.length ; i++){
            for(int j = 0; j < board[0].length() ; j++){
                if(board[i].charAt(j) == 'R'){
                    startRow = i;
                    startCol = j;
                }

            }

        }
        int answer = 0;
        answer = findG( board,  startRow,  startCol);
        return answer;
    }
    // 벽이나 'D'라면 멈추고, 멈췄을때 해당 칸이 'G'라면 움직임을 return
    public int findG(String[] board,int startRow, int startCol){
        int count = 0;
        int rowSize = board.length;
        int colSize = board[0].length();
        boolean [][] visited = new boolean[rowSize][colSize];

        Queue<int[]> queue = new ArrayDeque<>();
        // {현재Row, 현재Col, 움직인수}
        queue.offer(new int[] {startRow, startCol, 0});
        visited[startRow][startCol] = true;

        while(!queue.isEmpty()){
            int current[] = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int moveCount = current[2];

            if(board[currentRow].charAt(currentCol) == 'G'){
                return moveCount;
            }

            for(int direction = 0; direction < 4; direction++){
                int nextRow = currentRow;
                int nextCol = currentCol;

                while(true){
                    int moveRow = nextRow + dx[direction];
                    int moveCol = nextCol + dy[direction];

                    if(moveRow < 0 || moveRow >= rowSize || moveCol <0 || moveCol >= colSize){
                        break;
                    }
                    if(board[moveRow].charAt(moveCol) == 'D'){
                        break;
                    }
                    nextRow = moveRow;
                    nextCol = moveCol;

                }
                // 같은 위치에서 못움직일 때
                if(nextRow == currentRow && nextCol == currentCol){
                    continue;
                }
                //moveCount ++; 을 쓰면 안되고, 반드시 큐에 직접 moveCount+1을 넣어야한다.
                if(!visited[nextRow][nextCol]){
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol, moveCount+1});

                }

            }
        }
        return -1;


    }

}
