package standard.ch12.enumabstract;

import org.junit.jupiter.api.Test;

class TransportationTest {

    @Test
    public void abstractEnumTest() {
        System.out.println("Transportation.BUS.fare(100) = " + Transportation.BUS.fare(100));
        System.out.println("Transportation.SHIP.fare(100) = " + Transportation.SHIP.fare(100));
        System.out.println("Transportation.TRAIN.fare(100) = " + Transportation.TRAIN.fare(100));
        System.out.println("Transportation.AIRPLANE.fare(100) = " + Transportation.AIRPLANE.fare(100));
    }

    @Test
    public void myEnumTest() {
        MyTransportation bus1 = MyTransportation.BUS;
        MyTransportation bus2 = MyTransportation.BUS;
        MyTransportation train = MyTransportation.TRAIN;
        MyTransportation ship = MyTransportation.SHIP;
        MyTransportation airplane = MyTransportation.AIRPLANE;

        System.out.printf("bus1=%s, %d%n", bus1.name(), bus1.ordinal());
        System.out.printf("bus2=%s, %d%n", bus2.name(), bus2.ordinal());
        System.out.printf("train=%s, %d%n", train.name(), train.ordinal());
        System.out.printf("ship=%s, %d%n", ship.name(), ship.ordinal());
        System.out.printf("airplane=%s, %d%n", airplane.name(), airplane.ordinal());

        System.out.println("(bus1==bus2) = " + (bus1 == bus2));
        System.out.println("bus1.compareTo(train) = " + bus1.compareTo(train));
    }
}