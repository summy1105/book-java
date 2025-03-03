package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42579 lv3
public class BestAlbum {
    static class Song{
        int id;
        String genre;
        int count;
        public Song(int id, String genre, int count){
            this.id = id;
            this.genre = genre;
            this.count = count;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        List<Song> songs = new ArrayList<>();
        for(int i=0; i<plays.length; i++){
            songs.add(new Song(i, genres[i], plays[i]));
        }
        Map<String, List<Song>> genresMap = songs.stream().collect(Collectors.groupingBy(s->s.genre, Collectors.toList()));
        TreeMap<Integer, String> genresCount = new TreeMap<>();
        for (String genre : genresMap.keySet()) {
            List<Song> sorted = genresMap.get(genre).stream().sorted((s1, s2) -> s2.count - s1.count).collect(Collectors.toList());
            genresMap.put(genre, sorted);
            genresCount.put(sorted.stream().mapToInt(s -> s.count).sum(), genre);
        }

        List<Integer> answer = new ArrayList<>();
        while(!genresCount.isEmpty()){
            String genre = genresCount.pollLastEntry().getValue();
            List<Song> songsOfGenre = genresMap.get(genre);
            answer.add(songsOfGenre.get(0).id);
            if (songsOfGenre.size() > 1) {
                answer.add(songsOfGenre.get(1).id);
            }
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    @Test
    public void ex1() {
        int[] result = solution(
                new String[]{"classic", "pop", "classic", "classic", "pop"},
                new int[]{500, 600, 150, 800, 2500}
        );
        Assertions.assertThat(result).containsExactly(4, 1, 3, 0);
    }
}
