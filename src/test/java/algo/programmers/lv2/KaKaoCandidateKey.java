package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/42890 lv2

public class KaKaoCandidateKey {
    public int solution(String[][] relation) {
        HashSet<Integer> candidateKeySet = new HashSet<>();
        int rowLength = relation.length;
        int columnLength = relation[0].length;

        for (int columnCombination = 1; columnCombination < (1 << columnLength); columnCombination++) {
            HashSet<Integer> tupleSet = new HashSet<>();
            for(String[] relate : relation){
                String[] pickColumns = new String[Integer.bitCount(columnCombination)];
                int columnIdx = 0;
                for (int i=0; i<columnLength; i++){
                    if( ((1<<i) & columnCombination) != 0 ){
                        pickColumns[columnIdx++] = relate[i];
                    }
                }
                tupleSet.add(Arrays.hashCode(pickColumns));
            }

            if(tupleSet.size() != rowLength) continue;

            boolean isMinimality = true;
            for (int candidateKey : candidateKeySet) {
                if((candidateKey & columnCombination) == candidateKey) {
                    isMinimality = false;
                    break;
                }
            }

            if (isMinimality) {
                candidateKeySet.add(columnCombination);
            }

        }

        return candidateKeySet.size();
    }

    @Test
    public void ex1() {
        int result = solution(new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}});
        Assertions.assertThat(result).isEqualTo(2);
    }
}
