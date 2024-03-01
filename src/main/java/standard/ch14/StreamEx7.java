package standard.ch14;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class StreamEx7 {
    public static void main(String[] args) {
        StudentEx7[] stuArr = {
                new StudentEx7("가", true, 1, 1, 300),
                new StudentEx7("나", false, 1, 1, 250),
                new StudentEx7("다", true, 1, 1, 200),
                new StudentEx7("라", false, 1, 2, 150),
                new StudentEx7("마", true, 1, 2, 100),
                new StudentEx7("바", false, 1, 2, 50),
                new StudentEx7("사", false, 1, 3, 100),
                new StudentEx7("아", false, 1, 3, 150),
                new StudentEx7("자", true, 1, 3, 200),


                new StudentEx7("고", true, 2, 1, 300),
                new StudentEx7("노", false, 2, 1, 250),
                new StudentEx7("도", true, 2, 1, 200),
                new StudentEx7("로", false, 2, 2, 150),
                new StudentEx7("모", true, 2, 2, 100),
                new StudentEx7("보", false, 2, 2, 50),
                new StudentEx7("소", false, 2, 3, 100),
                new StudentEx7("오", false, 2, 3, 150),
                new StudentEx7("조", true, 2, 3, 200)
        };

        Map<Boolean, List<StudentEx7>> stuBySex = Stream.of(stuArr)
                    .collect(partitioningBy(StudentEx7::isMale));
        // male
        for(StudentEx7 s : stuBySex.get(true)) System.out.println(s);
        System.out.println();
        //female
        for(StudentEx7 s : stuBySex.get(false)) System.out.println(s);
        System.out.println();

        /////////////////////////////////////////////////////////////
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
                .collect(partitioningBy(StudentEx7::isMale, counting()));
        System.out.printf("Male : %d%n", stuNumBySex.get(true));
        System.out.printf("Female : %d%n", stuNumBySex.get(false));
        System.out.println();

        /////////////////////////////////////////////////////////////
        Map<Boolean, Optional<StudentEx7>> topScoreBySex = Stream.of(stuArr)
                .collect(partitioningBy(StudentEx7::isMale, maxBy(comparingInt(StudentEx7::getScore)) ));
        System.out.printf("Male : %s%n", topScoreBySex.get(true));
        System.out.printf("Female: %s%n", topScoreBySex.get(false));
        System.out.println();

        /////////////////////////////////////////////////////////////
        Map<Boolean, StudentEx7> topScoreBySex2 = Stream.of(stuArr)
                .collect(partitioningBy(StudentEx7::isMale,
                        collectingAndThen(maxBy(comparingInt(StudentEx7::getScore)), Optional::get)));
        System.out.printf("Male : %s%n", topScoreBySex2.get(true));
        System.out.printf("Female: %s%n", topScoreBySex2.get(false));
        System.out.println();

        /////////////////////////////////////////////////////////////
        Map<Boolean, Map<Boolean, List<StudentEx7>>> failedStuBySex = Stream.of(stuArr)
                .collect(partitioningBy(StudentEx7::isMale,
                    partitioningBy(s->s.getScore()<=100)
                ));
        // under 100 - male
        for(StudentEx7 s : failedStuBySex.get(true).get(true)) System.out.println(s);
        System.out.println();
        // under 100 - femal
        for(StudentEx7 s : failedStuBySex.get(false).get(true)) System.out.println(s);
        System.out.println();
    }
}

class StudentEx7 {
    String name;
    boolean isMale;
    int hak;
    int ban;
    int score;

    StudentEx7(String name, boolean isMale, int hak, int ban, int score){
        this.name= name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    public String toString(){
        return String.format("[%s, %s, %d-%d, %d]"
                , name, isMale?"M":"F", hak, ban, score);
    }

    String getName() { return name; }
    boolean isMale() { return isMale; }
    int getHak() { return hak; }
    int getBan() { return ban; }
    int getScore() { return score; }

    enum Level { HIGH, MID, LOW }
}