package modernaction.chap04_07_stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Trader {
    private final String name;
    private final String city;
}
