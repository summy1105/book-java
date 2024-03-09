package modernaction.chap04_07_stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
}
