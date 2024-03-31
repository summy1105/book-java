package modernaction.chap11;

import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Person {
    private Optional<Car> car = Optional.empty();
    private int age;

    public Person setCar(Car car) {
        this.car = Optional.ofNullable(car);
        return this;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optC -> optC.flatMap(Car::getInsurance))
                .map(optI -> optI.map(Insurance::getName))
                .flatMap(Optional::stream) // Stream<Optional<String>> -> Stream<String>
                .collect(Collectors.toSet());
    }

    public static String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p->p.getAge()>=minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<Insurance> nullSafeFindCheapestInsuranceUsingFlatMap(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p->car.map(c-> findCheapestInsurance(p,c)));
    }

    private static Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance().setName("cheapest insurance");
    }

    @Getter
    public static class Car{
        private Optional<Insurance> insurance = Optional.empty();

        public Car setInsurance(Insurance insurance) {
            this.insurance = Optional.ofNullable(insurance);
            return this;
        }
    }

    @Getter
    public static class Insurance {
        private String name;

        public Insurance setName(String name) {
            this.name = name;
            return this;
        }
    }
}
