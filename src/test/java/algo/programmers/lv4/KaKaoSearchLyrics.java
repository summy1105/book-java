package algo.programmers.lv4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/60060 lv4


public class KaKaoSearchLyrics {
    static class CharacterNode{
        char curChar;
        CharacterNode[] children;
        Map<Integer, Integer> wordLengthCount;

        public CharacterNode(char curChar) {
            this.curChar = curChar;
            children = new CharacterNode[26];
            wordLengthCount = new HashMap<>();
        }

        void addWordForward(String word, int depth){
            int length = word.length();
            Integer before = wordLengthCount.getOrDefault(length, 0);
            wordLengthCount.put(length, before + 1);
            if(length == depth)
                return;

            char childChar = word.charAt(depth);
            int childIdx = childChar - 'a';
            if(children[childIdx] == null)
                children[childIdx] = new CharacterNode(childChar);
            children[childIdx].addWordForward(word, depth+1);
        }

        int getMatchCountFromForward(String query, int depth){
            char childChar = query.charAt(depth);
            if('?' == childChar)
                return wordLengthCount.getOrDefault(query.length(), 0);

            int childIdx = childChar - 'a';
            if(children[childIdx] == null)
                children[childIdx] = new CharacterNode(childChar);
            return children[childIdx].getMatchCountFromForward(query, depth + 1);
        }
        //-------------

        void addWordBackward(String word, int depth){
            int length = word.length();
            Integer before = wordLengthCount.getOrDefault(length, 0);
            wordLengthCount.put(length, before + 1);
            if(length == depth)
                return;

            char childChar = word.charAt(length-depth-1);
            int childIdx = childChar - 'a';
            if(children[childIdx] == null)
                children[childIdx] = new CharacterNode(childChar);
            children[childIdx].addWordBackward(word, depth+1);
        }

        int getMatchCountFromBackward(String query, int depth){
            int length = query.length();
            char childChar = query.charAt(length-depth-1);
            if('?' == childChar)
                return wordLengthCount.getOrDefault(length, 0);

            int childIdx = childChar - 'a';
            if(children[childIdx] == null) return 0;
            return children[childIdx].getMatchCountFromBackward(query, depth + 1);
        }
    }

    public int[] solution(String[] words, String[] queries) {
        CharacterNode forwardRoot = new CharacterNode('?');
        CharacterNode backwardRoot = new CharacterNode('?');
        for (String word : words) {
            forwardRoot.addWordForward(word, 0);
            backwardRoot.addWordBackward(word, 0);
        }
        int[] answer = new int[queries.length];
        for(int i=0; i< queries.length; i++){
            String query = queries[i];
            if (query.charAt(0) == '?') {
                answer[i] = backwardRoot.getMatchCountFromBackward(query, 0);
            } else {
                answer[i] = forwardRoot.getMatchCountFromForward(query, 0);
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] expected = {3, 2, 4, 1, 0};
        int[] result = solution(words, queries);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex2() {
        String[] words = {"abc", "def", "ghi", "jklm"};
        String[] queries = {"???","????", "a??", "jk??"};
        int[] expected = {3, 1, 1, 1};
        int[] result = solution(words, queries);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex3(){
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao", "apple", "banana"};
        String[] queries = {"appl?", "ba???a"};
        int[] expected = {1, 1};
        int[] result = solution(words, queries);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex4(){
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"??????"};
        int[] expected = {1};
        int[] result = solution(words, queries);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
