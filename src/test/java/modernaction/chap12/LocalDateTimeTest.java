package modernaction.chap12;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LocalDateTimeTest {
    @Test
    public void localDateTest() {
        LocalDate date = LocalDate.of(2017, 9, 21);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int length = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();

        assertEquals(2017, year);
        assertEquals(Month.SEPTEMBER, month);
        assertEquals(21, day);
        assertEquals(DayOfWeek.THURSDAY, dow);
        assertEquals(30, length);
        assertFalse(leapYear);


         year = date.get(ChronoField.YEAR);
         int monthInt = date.get(ChronoField.MONTH_OF_YEAR);
         day = date.get(ChronoField.DAY_OF_MONTH);
         assertEquals(2017, year);
         assertEquals(9, monthInt);
         assertEquals(21, day);
    }

    @Test
    public void nowLocalDateTest() {
        TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
        TimeZone.setDefault(timeZone);

        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);
    }

    @Test
    public void localTimeTest() {
        LocalTime time = LocalTime.of(13, 45, 20);

        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        assertEquals(13, hour);
        assertEquals(45, minute);
        assertEquals(20, second);
    }

    @Test
    public void parseTest() {
        LocalDate date = LocalDate.parse("2017-09-21");
        LocalTime time = LocalTime.parse("13:45:20");
    }
}
