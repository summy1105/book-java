package standard.ch14;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class StudentEx8 extends StudentEx7 {

    StudentEx8(String name, boolean isMale, int hak, int ban, int score) {
        super(name, isMale, hak, ban, score);
    }

    Level getLevel() {
        if(getScore() >= 200) return Level.HIGH;
        else if(getScore() >= 100) return Level.MID;
        else return Level.LOW;
    }
}

public class StreamEx8 {
    public static void main(String[] args) {
        StudentEx8[] stuArr = {
                new StudentEx8("가", true, 1, 1, 300),
                new StudentEx8("나", false, 1, 1, 250),
                new StudentEx8("다", true, 1, 1, 200),
                new StudentEx8("라", false, 1, 2, 150),
                new StudentEx8("마", true, 1, 2, 100),
                new StudentEx8("바", false, 1, 2, 50),
                new StudentEx8("사", false, 1, 3, 100),
                new StudentEx8("아", false, 1, 3, 250),
                new StudentEx8("자", true, 1, 3, 200),

                new StudentEx8("고", true, 2, 1, 300),
                new StudentEx8("노", false, 2, 1, 250),
                new StudentEx8("도", true, 2, 1, 200),
                new StudentEx8("로", false, 2, 2, 150),
                new StudentEx8("모", true, 2, 2, 100),
                new StudentEx8("보", false, 2, 2, 50),
                new StudentEx8("소", false, 2, 3, 100),
                new StudentEx8("오", false, 2, 3, 250),
                new StudentEx8("조", true, 2, 3, 200)
        };

        //////////////////////////////////////////////////////////////////////////////
        Map<Integer, List<StudentEx8>> stuByBan = Stream.of(stuArr)
                    .collect(groupingBy(StudentEx8::getBan));
        for(List<StudentEx8> b : stuByBan.values()){
            for(StudentEx8 s : b){
                System.out.println(s);
            }
        }
        System.out.println();

        //////////////////////////////////////////////////////////////////////////////
        Map<StudentEx8.Level, List<StudentEx8>> stuByLevel = Stream.of(stuArr)
                    .collect(groupingBy(StudentEx8::getLevel));

        TreeSet<StudentEx8.Level> keySet = new TreeSet<>(stuByLevel.keySet());
        for(StudentEx8.Level key : keySet){
            System.out.println(key);
            for(StudentEx8 s: stuByLevel.get(key)){
                System.out.println(s);
            }
            System.out.println();
        }

        //////////////////////////////////////////////////////////////////////////////
        Map<StudentEx7.Level, Long> stuCntByLevel = Stream.of(stuArr)
                    .collect(groupingBy(StudentEx8::getLevel, counting()));
        for (StudentEx7.Level key : stuCntByLevel.keySet()){
            System.out.printf("%s -> %d  ", key.name(), stuCntByLevel.get(key));
        }
        System.out.println("\n");

        //////////////////////////////////////////////////////////////////////////////
        Map<String, StudentEx8> topStuByHakAndBan = Stream.of(stuArr)
                    .collect(groupingBy(s -> String.format("%s-%s",s.getHak(), s.getBan())
                            , collectingAndThen(
                                    maxBy((s1, s2) -> s1.getScore()>s2.getScore()? 1: -1)
                                    , Optional::get)
                    ));

        for(String key : topStuByHakAndBan.keySet()) {
            System.out.printf("%s -> %s\n", key, topStuByHakAndBan.get(key));
        }
        System.out.println("");

        //////////////////////////////////////////////////////////////////////////////
        Map<String, Set<StudentEx8.Level>> stuByScoreGroup = Stream.of(stuArr)
                    .collect(groupingBy(
                                    s-> String.format("%s-%s", s.getHak(), s.getBan()),
                                    mapping(s-> s.getLevel(), toSet())
                            ));
        for(String key : stuByScoreGroup.keySet()){
            System.out.printf("[%s] - %s", key, stuByScoreGroup.get(key));
            System.out.println();
        }
        System.out.println();
    }
}
