package modernaction.chap04_07_stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type{
        MEAT(0), FISH(1), OTHER(2);
        int value;

        Type(int value) {
            this.value = value;
        }

        public static Type getTypeByValue(int value) {
            switch (value){
                case 0: return MEAT;
                case 1: return FISH;
                case 2: return OTHER;
                default: throw new IllegalStateException("Unexpected value: " + value);
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
