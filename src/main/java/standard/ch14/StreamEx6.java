package standard.ch14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("A stu", 3, 300),
                new Student("B stu", 1, 200),
                new Student("C stu", 2, 100),
                new Student("D stu", 2, 150),
                new Student("E stu", 1, 200),
                new Student("F stu", 3, 290),
                new Student("G stu", 3, 180)
        };

        List<String> names = Stream.of(stuArr).map(Student::getName)
                                .collect(Collectors.toList());

        System.out.println(names);
        System.out.println();

        //////////////////////////////////////////////////////////////////
        Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

        for(Student s : stuArr2)
            System.out.println(s);
        System.out.println();

        ///////////////////////////////////////////////////////////////////
        Map<String, Student> stuMap = Stream.of(stuArr)
                        .collect(Collectors.toMap(s->s.getName(), p->p));

        for(String name : stuMap.keySet())
            System.out.println(name+"-"+stuMap.get(name));
        System.out.println();

        ////////////////////////////////////////////////////////////////////
        long count = Stream.of(stuArr).collect(counting()); // counting() == Collectors.summingLong(e->1L)
        long totalScore = Stream.of(stuArr).collect(summingInt(Student::getTotalScore));
        System.out.println(count);
        System.out.println(totalScore);
        System.out.println();

        //////////////////////////////////////////////////////////////////////
        totalScore = Stream.of(stuArr)
                    .collect(reducing(0, Student::getTotalScore, Integer::sum));
        System.out.println(totalScore);
        System.out.println();

        ////////////////////////////////////////////////////////////////////////
        Optional<Student> topStudent = Stream.of(stuArr)
                        .collect(maxBy(new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return o1.getTotalScore()-o2.getTotalScore();
                            }
                        }));
        System.out.println(topStudent.get());
        System.out.println();

        //////////////////////////////////////////////////////////////////////////
        IntSummaryStatistics stat = Stream.of(stuArr)
                .collect(summarizingInt(s -> s.getTotalScore()));
        System.out.println(stat);
        System.out.println();

        //////////////////////////////////////////////////////////////////////////
        String stuNames = Stream.of(stuArr).map(Student::getName).collect(joining(",", "[", "]"));
        System.out.println(stuNames);
        System.out.println();

    }

    static class Student implements Comparable<Student> {
        String name;
        int ban;
        int totalScore;

        Student(String name, int ban, int totalScore){
            this.name= name;
            this.ban = ban;
            this.totalScore = totalScore;
        }

        public String toString(){
            return String.format("[%s, %d, %d]", name, ban, totalScore);
        }

        String getName() { return name; }
        int getBan() { return ban; }
        int getTotalScore() { return totalScore; }

        @Override
        public int compareTo(Student s) {
            return s.totalScore - this.totalScore;
        }
    }
}
