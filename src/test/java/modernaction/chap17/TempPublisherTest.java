package modernaction.chap17;

import modernaction.chap17.flow_api.TempPublisher;
import modernaction.chap17.flow_api.TempSubscriber;
import org.junit.jupiter.api.Test;

class TempPublisherTest {

    @Test
    public void getTemperatureTest() {
        TempPublisher.getTemperatures("New York").subscribe(new TempSubscriber());
    }

    @Test
    public void getCelsiusTemperaturesTest() {
        TempPublisher.getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
    }
}