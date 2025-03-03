package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/17679 lv2
public class KaKaoFriendsFourBlocks {
    public int solution(int m, int n, String[] board) {
        char[][] charBoard = new char[m][];
        for(int row=0; row< board.length; row++){
            charBoard[row] = board[row].toCharArray();
        }
        int answer = 0;
        for(boolean find = true; find; ){
            find = false;
            // block 찾기
            boolean[][] blockToRemove = new boolean[m][n];
            int findBlockCnt = findBlock(charBoard, m, n, blockToRemove);
            if(findBlockCnt>0) {
                find = true;
                // block 제거하기
                removeBlock(charBoard, m, n, blockToRemove);
                answer += findBlockCnt;
            }
        }
        return answer;
    }

    private int findBlock(char[][] board, int m, int n, boolean[][] blockToRemove) {
        int findBlockCnt = 0;
        boolean[][] visit = new boolean[m][n];

        for(int row=m-1; row>0; row--){
            for(int col = n-1; col>0; col--){
                if(!visit[row][col] && board[row][col]!='-'){
                    int bfs = bfs(board, visit, board[row][col], row, col, blockToRemove);
                    findBlockCnt += bfs;
                }
            }
        }
        return findBlockCnt;
    }

    private int bfs(char[][] board, boolean[][] visit, char charBlock, int row, int col, boolean[][] removeBlock){
        int count =0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if( cur[0]>0 && cur[1]>0
                    && board[cur[0]][cur[1]-1] == charBlock
                    && board[cur[0]-1][cur[1]-1] == charBlock
                    && board[cur[0]-1][cur[1]] == charBlock){
                if(!visit[cur[0]][cur[1]]){
                    visit[cur[0]][cur[1]] = true;
                    removeBlock[cur[0]][cur[1]] = true;
                    count++;
                }
                if(!visit[cur[0]][cur[1]-1]) {
                    visit[cur[0]][cur[1]-1] = true;
                    removeBlock[cur[0]][cur[1]-1] = true;
                    queue.add(new int[]{cur[0], cur[1] - 1});
                    count++;
                }
                if(!visit[cur[0]-1][cur[1]-1]) {
                    visit[cur[0]-1][cur[1]-1] = true;
                    removeBlock[cur[0]-1][cur[1]-1] = true;
                    queue.add(new int[]{cur[0]-1, cur[1] - 1});
                    count++;
                }
                if(!visit[cur[0]-1][cur[1]]) {
                    visit[cur[0]-1][cur[1]]= true;
                    removeBlock[cur[0]-1][cur[1]]= true;
                    queue.add(new int[]{cur[0]-1, cur[1]});
                    count++;
                }
            }
        }

        return count;
    }

    private void removeBlock(char[][] board, int m, int n, boolean[][] blockToRemove){
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (!blockToRemove[row][col]) continue;

                int nextRow = row-1;
                while (nextRow>0 && blockToRemove[nextRow][col]) nextRow--;
                if(nextRow>=0){
                    board[row][col] = board[nextRow][col];
                    board[nextRow][col] = '-';
                    blockToRemove[nextRow][col] = true;
                }else {
                    board[row][col] = '-';
                }
            }
        }
    }

    @Test
    public void ex1() {
        int result = solution(4, 5, new String[]{
                "CCBDE",
                "AAADE",
                "AAABF",
                "CCBBF"
        });
        Assertions.assertThat(result).isEqualTo(14);
    }

    @Test
    public void ex2() {
        int result = solution(6, 6, new String[]{
                "TTTANT",
                "RRFACC",
                "RRRFCC",
                "TRRRAA",
                "TTMMMF",
                "TMMTTJ"
        });
        Assertions.assertThat(result).isEqualTo(15);
    }
}
