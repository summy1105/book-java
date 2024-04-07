package modernaction.chap11;

import org.junit.jupiter.api.Test;

import java.util.*;

import static modernaction.chap11.Person.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonOptionalTest {

    @Test
    public void optionalMapTest() {
        assertDoesNotThrow(()->{
            Optional<Insurance> optInsurance = Optional.empty();
            Optional<String> name = optInsurance.map(Insurance::getName); // optInsurance 가 empty면 Optionla.empty()반환
            assertFalse(name.isPresent());
        });
    }

    @Test
    public void optionalFlatMapTest() {
        Optional<Person> person = Optional.empty();
        String carInsuranceName = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        assertEquals("Unknown", carInsuranceName);
    }

    @Test
    public void getCarInsuranceNamesTest() {
        List<Person> persons = Arrays.asList(new Person(),
                new Person().setCar(new Car().setInsurance(new Insurance().setName("Insurance test"))));

        Set<String> carInsuranceNames = getCarInsuranceNames(persons);
        carInsuranceNames.stream().forEach(System.out::println);
        assertEquals(1, carInsuranceNames.size());
    }

    @Test
    public void nullSafeFindCheapestInsuranceTest() {
        Optional<Insurance> cheapestInsurance = nullSafeFindCheapestInsurance(Optional.ofNullable(new Person()), Optional.ofNullable(new Car()));
        assertEquals(cheapestInsurance.map(Insurance::getName).orElse("fail"), "cheapest insurance");

        Optional<Insurance> nullInsurance = nullSafeFindCheapestInsurance(Optional.empty(), Optional.ofNullable(new Car()));
        assertEquals(nullInsurance, Optional.empty());


        nullInsurance = nullSafeFindCheapestInsuranceUsingFlatMap(Optional.ofNullable(new Person()), Optional.empty());
        assertEquals(nullInsurance, Optional.empty());

        cheapestInsurance = nullSafeFindCheapestInsuranceUsingFlatMap(Optional.ofNullable(new Person()), Optional.ofNullable(new Car()));
        assertEquals(cheapestInsurance.map(Insurance::getName).orElse("fail"), "cheapest insurance");
    }

    @Test
    public void getCarInsuranceNameTest() {
        Person person = new Person().setAge(24).setCar(new Car().setInsurance(new Insurance().setName("minAge Test")));
        String carInsuranceName = getCarInsuranceName(Optional.of(person), 20);
        assertEquals(carInsuranceName, "minAge Test");

        String carInsuranceName2 = getCarInsuranceName(Optional.of(person), 30);
        assertEquals(carInsuranceName2, "Unknown");
    }

    @Test
    public void quiz11_3() {
        Properties param = new Properties();
        param.setProperty("a", "5");
        param.setProperty("b", "true");
        param.setProperty("c", "-3");

        assertEquals(5, readDuration(param, "a"));
        assertEquals(0, readDuration(param, "b"));
        assertEquals(0, readDuration(param, "c"));
        assertEquals(0, readDuration(param, "d"));
    }

    public int readDuration(Properties props, String key) {
            return Optional.ofNullable(props.getProperty(key))
                    .flatMap(OptionalUtility::stringToInt)
                    .filter(i->i>0)
                    .orElse(0);
    }
}