package standard.ch12.enumabstract;

/**
 * enum 기본 구조
 * @param <T>
 */
abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T>{
    static int ID = 0;

    int ordinal;
    String name;

    public int ordinal(){return ordinal;}

    MyEnum(String name) {
        this.name = name;
        ordinal = ID++;
    }

    public int compareTo(T t) {
        return ordinal - t.ordinal();
    }
}
