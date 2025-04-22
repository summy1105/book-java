package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// https://school.programmers.co.kr/learn/courses/30/lessons/150366 lv3

public class KaKaoMergeTable {
    int MAX = 50;
    String[][] cellValues = new String[MAX+1][MAX+1];
    int[][][] mergeLink = new int[MAX+1][MAX+1][2];
    {
        for(int r=0; r<=MAX; r++){
            for(int c=0; c<=MAX; c++){
                mergeLink[r][c][0] = r;
                mergeLink[r][c][1] = c;
            }
        }
    }
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        for(String command : commands){
            String[] attributes = command.split(" ");

            if("UPDATE".equals(attributes[0]) && attributes.length>=4){
                int r1 = Integer.valueOf(attributes[1]);
                int c1 = Integer.valueOf(attributes[2]);
                String value = attributes[3];

                int rootRow = mergeLink[r1][c1][0];
                int rootCol = mergeLink[r1][c1][1];
                for (int r=1; r<=MAX; r++){
                    for(int c=1; c<=MAX; c++){
                        if(mergeLink[r][c][0] == rootRow && mergeLink[r][c][1] == rootCol){
                            cellValues[r][c] = value;
                        }
                    }
                }
            }else if("UPDATE".equals(attributes[0])){
                for(int r=1; r<=MAX; r++){
                    for(int c=1; c<=MAX; c++){
                        if(attributes[1].equals(cellValues[r][c])){
                            cellValues[r][c] = attributes[2];
                        }
                    }
                }
            }else if("MERGE".equals(attributes[0])){
                int r1 = Integer.valueOf(attributes[1]);
                int c1 = Integer.valueOf(attributes[2]);
                int r2 = Integer.valueOf(attributes[3]);
                int c2 = Integer.valueOf(attributes[4]);

                int rootRow = mergeLink[r1][c1][0];
                int rootCol = mergeLink[r1][c1][1];
                int changeRowRoot = mergeLink[r2][c2][0];
                int changeColRoot = mergeLink[r2][c2][1];

                if(rootRow == changeRowRoot && rootCol == changeColRoot)
                    continue;

                String mergeCellValue = cellValues[rootRow][rootCol] != null ? cellValues[rootRow][rootCol] : cellValues[changeRowRoot][changeColRoot];
                for(int r=1; r<=MAX; r++){
                    for(int c=1; c<=MAX; c++){
                        if(mergeLink[r][c][0] == rootRow && mergeLink[r][c][1] == rootCol){
                            cellValues[r][c] = mergeCellValue;
                        }
                        if(mergeLink[r][c][0] == changeRowRoot && mergeLink[r][c][1] == changeColRoot){
                            mergeLink[r][c][0] = rootRow;
                            mergeLink[r][c][1] = rootCol;
                            cellValues[r][c] = mergeCellValue;
                        }
                    }
                }
            }else if("UNMERGE".equals(attributes[0])){
                int r1 = Integer.valueOf(attributes[1]);
                int c1 = Integer.valueOf(attributes[2]);

                int rootR = mergeLink[r1][c1][0];
                int rootC = mergeLink[r1][c1][1];
                String value = cellValues[rootR][rootC];
                for(int r =1; r<=MAX; r++){
                    for(int c=1; c<=MAX; c++){
                        if(mergeLink[r][c][0] == rootR && mergeLink[r][c][1] == rootC){
                            mergeLink[r][c][0] = r;
                            mergeLink[r][c][1] = c;
                            cellValues[r][c] = null;
                        }
                    }
                }
                cellValues[r1][c1] = value;
            }else if("PRINT".equals(attributes[0])){
                int row = Integer.valueOf(attributes[1]);
                int col = Integer.valueOf(attributes[2]);
                String value = cellValues[row][col] == null ? "EMPTY" : cellValues[row][col];
                answer.add(value);
            }
        }
        return answer.toArray(new String[answer.size()]);
    }

    @Test
    public void testCase1() {
        String[] commands = {
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta",
                "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle",
                "MERGE 1 2 1 3",
                "MERGE 1 3 1 4",
                "UPDATE korean hansik",
                "UPDATE 1 3 group",
                "UNMERGE 1 4",
                "PRINT 1 3",
                "PRINT 1 4"
        };
        String[] expected = {"EMPTY", "group"};

        String[] result = solution(commands);
        assertThat(result).containsExactly(expected);
    }

    @Test
    public void testCase2() {
        String[] commands = {
                "UPDATE 1 1 a",
                "UPDATE 1 2 b",
                "UPDATE 2 1 c",
                "UPDATE 2 2 d",
                "MERGE 1 1 1 2",
                "MERGE 2 2 2 1",
                "MERGE 2 1 1 1",
                "PRINT 1 1",
                "UNMERGE 2 2",
                "PRINT 1 1"
        };
        String[] expected = {"d", "EMPTY"};

        String[] result = solution(commands);
        assertThat(result).containsExactly(expected);
    }

}
