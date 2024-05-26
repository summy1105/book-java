package modernaction.chap17.flow_api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
@Getter
public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public static TempInfo fetch(String town) {
        if(random.nextInt(50) == 0)
            throw new RuntimeException("Error!");
        return new TempInfo(town, random.nextInt(100)-30);
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }
}
