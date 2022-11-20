package ch14;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamEx1 {
    Integer nonStaticInt = 5;
    static Integer staticInt = 3;

    public static void main(String[] args) {
        StreamEx1 streamEx1 = new StreamEx1();
        Stream<Student> studentStream = Stream.of(
                new Student("a", 3, 300)
                , new Student("b", 1, 200)
                , new Student("c", 2, 100)
                , new Student("e", 1, 200)
                , new Student("f", 3, 290)
                , new Student("g", 3, 180)
//                , streamEx1.new Student("d", 2, 150) // Student가 non-static이면 앞에 생성된 instance를 넣어줘야함
        );

        studentStream.sorted(Comparator.comparing(Student::getBan).thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        InnerClass innerClass = streamEx1.new InnerClass();
        System.out.println(innerClass.num);
        innerClass.doSomething();
        System.out.println(StreamEx1.InnerClass.class);
    }

    void doSomething(){
        Student temp = new Student("a", 100, 200);
    }

    class InnerClass {
        public int num = 1;
        public String str = "temp";

        void doSomething(){
            System.out.println();
            System.out.println(nonStaticInt);
            System.out.println(staticInt);
        }
    }

    static class Student implements Comparable<Student>{
        String name;
        int ban;
        int totalScore;

        Student(){
            this("void", 0, 0);
        }

        Student(String name, int ban, int totalScore) {
            this.name = name;
            this.ban = ban;
            this.totalScore = totalScore;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d, %d]", name, ban, totalScore);
        }

        @Override
        public int compareTo(Student o) {
            return o.totalScore - this.totalScore;
        }

        public String getName() {
            return name;
        }

        public int getBan() {
            return ban;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public void doInnerSomething(){
//            System.out.println(nonStaticInt); //inner class가 static이면 instance가 아니기 때문에, non-static상태의 outer class의 member접근 불가
            System.out.println(staticInt);
        }
    }
}
