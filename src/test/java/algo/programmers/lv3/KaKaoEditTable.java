package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/42890 lv3

public class KaKaoEditTable {
    static class Row{
        int number;
        Row above;
        Row below;

        public Row(int number, Row above) {
            this.number = number;
            this.above = above;
        }
    }
    public String solution(int n, int k, String[] cmd) {
        Row pointer = new Row(0, null);
        Row cursor = pointer;
        for (int i = 1; i < n; i++) {
            pointer.below = new Row(i, pointer);
            pointer = pointer.below;
            if(i==k) cursor=pointer;
        }


        Stack<Row> stack = new Stack<>();
        for (String command : cmd) {
            char operation = command.charAt(0);
            switch (operation) {
                case ('U')->{
                    if(cursor==null) break;
                    Integer count = Integer.valueOf(command.substring(2));
                    for (int i = 0; i < count && cursor.above!=null; i++)
                        cursor = cursor.above;
                }
                case ('D')->{
                    if(cursor==null) break;
                    Integer count = Integer.valueOf(command.substring(2));
                    for (int i = 0; i < count && cursor.below!=null; i++)
                        cursor = cursor.below;
                }

                case ('C')->{
                    stack.push(cursor);
                    if (cursor.above != null) {
                        cursor.above.below = cursor.below;
                    }
                    if (cursor.below != null) {
                        cursor.below.above = cursor.above;
                        cursor = cursor.below;
                    } else {
                        cursor = cursor.above;
                    }
                }

                case ('Z')->{
                    Row restored = stack.pop();
                    if (restored.above != null) {
                        restored.above.below = restored;
                    }
                    if (restored.below != null) {
                        restored.below.above = restored;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean[] isDeleted = new boolean[n];
        while (!stack.isEmpty()) {
            isDeleted[stack.pop().number] = true;
        }
        for (int i = 0; i < n; i++) {
            sb.append(isDeleted[i] ? 'X' : 'O');
        }
        return sb.toString();
    }

    @Test
    public void ex1() {
        String result = solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"});
        Assertions.assertThat(result).isEqualTo("OOOOXOOO");
    }

    @Test
    public void ex2() {
        String result = solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"});
        Assertions.assertThat(result).isEqualTo("OOXOXOOO");
    }
}
