package algo.programmers.lv4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/17685 lv4


public class KaKaoAutocomplete {
    static class WordTree{
        WordTree[] children;
        int count;

        WordTree(){
            children = new WordTree[26];
            count=0;
        }

        static WordTree root = new WordTree();

        static WordTree getRoot(){
            root = new WordTree();
            return root;
        }

        void insert(String word){
            WordTree point=root;
            for(int i=0; i<word.length(); i++){
                int cIdx = word.charAt(i)-'a';
                if (point.children[cIdx] == null){
                    point.children[cIdx] = new WordTree();
                }
                point = point.children[cIdx];
                point.count++;
            }
            return ;
        }

        int getPrefixCount(String word){
            StringBuilder prefix = new StringBuilder();
            WordTree point=root;

            for(int i=0; i<word.length(); i++){
                prefix.append(word.charAt(i));
                int cIdx = word.charAt(i)-'a';
                point = point.children[cIdx];
                if(point.count==1 || word.equals(prefix))
                    break;
            }

            return prefix.length();
        }
    }
    
    public int solution(String[] words) {
        WordTree root = WordTree.getRoot();

        for (String word : words) {
            root.insert(word);
        }
        int answer=0;
        for (String word : words) {
            answer += root.getPrefixCount(word);
        }
        return answer;
    }

    @Test
    public void ex1() {
        String[] words = {"go", "gone", "guild"};
        int result = solution(words);
        Assertions.assertThat(result).isEqualTo(7);
    }

    @Test
    public void ex2() {
        String[] words = {"abc", "def", "ghi", "jklm"};
        int result = solution(words);
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void ex3() {
        String[] words = {"word", "war", "warrior", "world"};
        int result = solution(words);
        Assertions.assertThat(result).isEqualTo(15);
    }
}
