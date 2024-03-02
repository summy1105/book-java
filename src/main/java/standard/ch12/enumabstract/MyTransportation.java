package standard.ch12.enumabstract;

/**
 * Transportation enum 형태
 */
abstract class MyTransportation extends MyEnum {

    static final MyTransportation BUS = new MyTransportation("BUS", 100) {
        @Override
        int fare(int distance) { return distance * BASIC_FARE; }
    };

    static final MyTransportation TRAIN = new MyTransportation("TRAIN", 150) {
        @Override
        int fare(int distance) { return distance * BASIC_FARE; }
    };

    static final MyTransportation SHIP = new MyTransportation("SHIP", 100) {
        @Override
        int fare(int distance) { return distance * BASIC_FARE; }
    };

    static final MyTransportation AIRPLANE = new MyTransportation("AIRPLANE", 300) {
        @Override
        int fare(int distance) { return distance * BASIC_FARE; }
    };


    abstract int fare(int distance);

    protected final int BASIC_FARE;

    MyTransportation(String name, int basicFare) {
        super(name);
        BASIC_FARE = basicFare;
    }

    public String name() { return name; }
    public String toString() { return name; }
}
