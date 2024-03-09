package modernaction.chap04_07_stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    @DisplayName("2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리")
    public void test1() {
        List<Transaction> resultList = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        resultList.forEach(System.out::println);
    }

    @Test
    @DisplayName("거래자가 근무하는 모든 도시를 중복 없이 나열하시오")
    public void test2() {
        List<String> cities = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        cities.forEach(System.out::println);
    }

    @Test
    @DisplayName("케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오. -> return Trade list")
    public void test3() {
        List<Trader> traders = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getTrader())
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        traders.forEach(System.out::println);
    }

    @Test
    @DisplayName("모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.")
    public void test4() {
        String reduce = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" "));
//                .reduce("", String::concat);
//                .collect(Collectors.toList());
        System.out.println("reduce = " + reduce);
    }

    @Test
    @DisplayName("밀라노에 거주자가 있는가?")
    public void test5() {
        boolean milan = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println("milan = " + milan);
    }

    @Test
    @DisplayName("케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오")
    public void test6() {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("전체 트랜잭션 중 최댓값은?")
    public void test7() {
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        max.ifPresent(System.out::println);
        Optional<Transaction> maxTransaction = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));
        maxTransaction.ifPresent(System.out::println);
    }

    @Test
    @DisplayName("전체 트랜잭션 중 최소값은?")
    public void test8() {
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo);
        min.ifPresent(System.out::println);
        Optional<Transaction> minTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        minTransaction.ifPresent(System.out::println);
    }
}