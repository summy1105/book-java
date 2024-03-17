package modernaction.chap08;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CollectionTest {

    @Test
    public void test_UnsupportedOperationException() {
        List<String> friends = Arrays.asList("Raphael", "Olivia"); //fixed-size list
        friends.set(0, "Richard");
        Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> friends.add("Thibaut"));

        HashSet<String> friendsSet = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
        friendsSet.add("Richard"); // 가능 exception X

        List<String> friends2 = List.of("Raphael", "Olivia", "Thibaut");// Returns an unmodifiable list
    }

    @Test
    public void test2() {
        Set<String> friendsSet = Set.of("Raphael", "Olivia", "Thibaut"); //Returns an unmodifiable set
        System.out.println("friendsSet = " + friendsSet);
        Assertions.assertThrowsExactly(UnsupportedOperationException.class, () -> friendsSet.add("Thibaut"));

        Map<String, Integer> ageOfFriends
                = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26); // Returns an unmodifiable map
        System.out.println("ageOfFriends = " + ageOfFriends);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ageOfFriends.put("Richard", 31));

        Map<String, Integer> ageOfFriends2 = Map.ofEntries(Map.entry("Raphael", 30)
                , Map.entry("Olivia", 25)
                , Map.entry("Thibaut", 26)); // Returns an unmodifiable map
        System.out.println("ageOfFriends2 = " + ageOfFriends2);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> ageOfFriends2.put("Richard", 31));
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    static class Transaction{
        private final String referenceCode;
    }

    List<Transaction> transactions = new ArrayList<>();
    {
        transactions.add(new Transaction("1reference"));
        transactions.add(new Transaction("1reference"));
        transactions.add(new Transaction("reference"));
        transactions.add(new Transaction("reference2"));
        transactions.add(new Transaction("3reference"));
    }


    @Test
    public void test_removeIf() {
        Assertions.assertThrows(ConcurrentModificationException.class, ()->{
            for (Transaction transaction : transactions) {
                if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                    transactions.remove(transaction);
                }
            }
        });
        Assertions.assertDoesNotThrow(()-> {
            for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
                Transaction transaction = iterator.next();
                if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                    iterator.remove();
                }
            }
        });
        transactions.stream().forEach(System.out::println);
        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
    }

    @Test
    public void test_replaceAll() {
        List<String> referenceCodes = transactions.stream().map(t -> t.getReferenceCode()).collect(Collectors.toList());

        referenceCodes.stream()
                .map(code-> Character.toUpperCase(code.charAt(0))+code.substring(1))
                .collect(Collectors.toList()).forEach(System.out::println); // 새컬렉션을 만듬 -> 기존 컬렉션이 대체X

        for(ListIterator<String> iterator = referenceCodes.listIterator();
            iterator.hasNext(); ){
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0))+code.substring(1));
        } // iterator 사용

        referenceCodes.replaceAll(code->Character.toUpperCase(code.charAt(0))+code.substring(1));// lambda 사용
    }

    @Test
    public void test_forEach() {
        Map<String, Integer> ageOfFriends
                = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26); // Returns an unmodifiable map

        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        System.out.println();
        ageOfFriends.forEach((friend, age)->System.out.println(friend + " is " + age + " years old"));
    }

    @Test
    public void test_mapOrder() {
        Map<String, String> favouriteMovies = Map.ofEntries(Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond"));

        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
        System.out.println();
        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }

    @Test
    public void test_compute() {
        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.putAll(Map.ofEntries(Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond")));

        favouriteMovies.computeIfPresent("Richard", (key, value)-> value+"NONE");
        favouriteMovies.entrySet().stream()
                .forEach(System.out::println);
        System.out.println();

        favouriteMovies.computeIfAbsent("Richard", (key)->"NONE"+key);
        favouriteMovies.entrySet().stream()
                .forEach(System.out::println);
        System.out.println();

        favouriteMovies.compute("Richard", (key, value)-> null);
        favouriteMovies.entrySet().stream()
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void test_merge() {
        Map<String, String> family = Map.ofEntries(Map.entry("Teo", "Star Wars"),
                Map.entry("Cristina", "James Bond"));
        Map<String, String> friends = Map.ofEntries(Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"));

        HashMap<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & 1   " + movie2));
        everyone.entrySet().forEach(System.out::println);
    }

    @Test
    public void quiz8_2() {
        HashMap<String, Integer> movies = new HashMap<>();
        movies.put("James Bond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);

        movies.entrySet().removeIf(entry -> entry.getValue() < 10);
        System.out.println("movies = " + movies);
    }

    @Test
    public void test_ConcurrentHashMap() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("James Bond", 20L);
        map.put("Matrix", 15L);
        map.put("Harry Potter", 5L);

        // parallelismThreshold : map의 entry갯수와, ForkJoinPool.getCommonPoolParallelism()의 수와 연관이 있다.
        // 1로 하면, 모든 엔트리 갯수로 thread 배치 수가 생기는 듯 하다. 하지만 getCommonPoolParallelism 수를 넘어서지 않는다.
        Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(1L, Long::max));
        System.out.println("maxValue.orElseGet() = " + maxValue.orElse(0L));

        int size = map.size();
        long l = map.mappingCount();

        ConcurrentHashMap.KeySetView<String, Long> strings = map.keySet();
        strings.remove("Matrix");
        System.out.println("strings = " + strings);
        System.out.println("map = " + map); // key집합이 변하면 기존 map객체에도 영향을 미침

        ConcurrentHashMap.KeySetView<String, Boolean> objects = ConcurrentHashMap.newKeySet(); // 기존 map집합이 변하지 않게 newKeySet 메소드 사용
        objects.addAll(map.keySet());
        objects.remove("Harry Potter");
        System.out.println("objects = " + objects);
        System.out.println("map = " + map);

    }
}
