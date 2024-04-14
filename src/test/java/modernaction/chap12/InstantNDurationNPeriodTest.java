package modernaction.chap12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Locale;
import java.util.TimeZone;

public class InstantNDurationNPeriodTest {

    @Test
    public void instantTest() {
        Instant instant = Instant.ofEpochSecond(3);
        System.out.println("instant = " + instant);

        Instant instant1 = Instant.ofEpochSecond(3, 0);
        System.out.println("instant1 = " + instant1);

        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);
        System.out.println("instant2 = " + instant2);

        Instant instant3 = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println("instant3 = " + instant3);

        Instant instant4 = Instant.ofEpochSecond(2, 100_000_000);
        System.out.println("instant4 = " + instant4);
    }

    @Test
    public void unsupportedTemporalTypeExceptionTest() {
        Assertions.assertThrows(UnsupportedTemporalTypeException.class
                , ()->{
                    int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
                    System.out.println("day = " + day);
                });

        //NANO_OF_SECOND
        //MICRO_OF_SECOND
        //MILLI_OF_SECOND
        Assertions.assertDoesNotThrow(()->{
            int second1 = Instant.now().get(ChronoField.NANO_OF_SECOND);
            int second2 = Instant.now().get(ChronoField.MICRO_OF_SECOND);
            int second3 = Instant.now().get(ChronoField.MILLI_OF_SECOND);
        });
    }

    @Test
    public void durationBetweenTest() {
        // Duration 클래스는 초(nano 초)단위로 표현
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        Duration between1 = Duration.between(nowLocalDateTime, nowLocalDateTime.plusSeconds(3));
        System.out.println("between1 = " + between1);

        LocalTime nowLocalTime = LocalTime.now();
        Duration between2 = Duration.between(nowLocalTime, nowLocalTime.minusMinutes(3));
        System.out.println("between2 = " + between2);

        Instant nowInstant = Instant.now();
        Duration between3 = Duration.between(nowInstant, nowInstant.plusNanos(300_000_000));
        System.out.println("between3 = " + between3);

        Assertions.assertThrowsExactly(UnsupportedTemporalTypeException.class, ()->{
            Duration between = Duration.between(LocalDate.now(), LocalDate.now());
            System.out.println("between = " + between);
        });
    }

    @Test
    public void periodBetweenTest() {
        LocalDate nowLocalDate = LocalDate.now();
        Period between = Period.between(nowLocalDate.minusMonths(3), nowLocalDate);
        System.out.println("between = " + between);
    }

    @Test
    public void withAttributeTest() {
        LocalDate date1 = LocalDate.of(2017, 9, 21);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2);
        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
        System.out.println("date3 = " + date3);
        System.out.println("date4 = " + date4);

        Assertions.assertThrows(UnsupportedTemporalTypeException.class, () -> {
            Instant with = Instant.now().with(ChronoField.DAY_OF_MONTH, 3);
        });
        Assertions.assertThrows(UnsupportedTemporalTypeException.class, () -> {
            LocalDate with = date1.with(ChronoField.MICRO_OF_SECOND, 100);
        });
    }

    @Test
    public void temporalAdjustersTest(){
        LocalDate nowDate = LocalDate.now();
        LocalDate date1 = nowDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date2 = date1.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate date3 = date2.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
        System.out.println("date3 = " + date3);

        LocalDate lastDay = LocalDate.of(2024, 1, 31);
        LocalDate date4 = lastDay.plusMonths(3);
        System.out.println("date4 = " + date4);

        Period between = Period.between(lastDay, date4);
        System.out.println("between = " + between.getMonths());
        // 첫번째 파람의 dayOfMonth날짜 가 두번째 파람 dayOfMonth 날짜보다 크면 between month가 작음
        // 만약 첫번째 파람의 date가 31일 이면,  between month값이 예상한 값보다 -1 이 된다.
    }

    @Test
    public void quiz12_2() {
        LocalDate date = LocalDate.of(2024, 4, 12);
        LocalDate nextDay = date.with(new NextWorkingDay());
        Assertions.assertEquals(DayOfWeek.MONDAY, nextDay.getDayOfWeek());

        LocalDate date2 = date.with(ChronoField.DAY_OF_MONTH, 11);
        LocalDate nextDay2 = date2.with(new NextWorkingDay());
        Assertions.assertEquals(DayOfWeek.FRIDAY, nextDay2.getDayOfWeek());

        LocalDate nextDay3 = date.with((Temporal temporal) -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        Assertions.assertEquals(DayOfWeek.MONDAY, nextDay3.getDayOfWeek());
    }

    public class NextWorkingDay implements TemporalAdjuster{
        @Override
        public Temporal adjustInto(Temporal temporal) {
            Temporal nextDay = temporal.plus(1, ChronoUnit.DAYS);
            int i = nextDay.get(ChronoField.DAY_OF_WEEK);
            if(i<= 5){
                return nextDay;
            }
            return nextDay.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        }
    }

    @Test
    public void dateTimeFormatterTest() {
        LocalDate date = LocalDate.of(2014, 4, 18);
        String format = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("format = " + format); // 20140418

        String format1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("format1 = " + format1); // 2014-04-18

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formattedDate = date1.format(formatter);
        System.out.println("formattedDate = " + formattedDate);
        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
        Assertions.assertEquals(date1, parsedDate);
    }

    @Test
    public void dateTimeFormatterTest2() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, MMMM yyyy", Locale.ITALIAN); // Locale 사용 -> 사용언어 바꿔줌
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MMMM dd일", Locale.KOREA); // 월(month)만 자동 format되는듯.
        LocalDate date1 = LocalDate.of(2014, 11, 18);
        String formattedDate = date1.format(formatter);
        System.out.println("formattedDate = " + formattedDate);

        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
        Assertions.assertEquals(date1, parsedDate);
    }

    @Test
    public void dateTimeFormatterBuilderTest() {
        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        LocalDate date1 = LocalDate.of(2014, 11, 18);
        String formattedDate = date1.format(italianFormatter);
        System.out.println("formattedDate = " + formattedDate);
    }

    @Test
    public void zoneIdTest() {
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(seoulZone);
        System.out.println("zonedDateTime = " + zonedDateTime);

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zonedDateTime1 = date.atStartOfDay(romeZone);
        System.out.println("zonedDateTime1 = " + zonedDateTime1);

        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(18, 13, 45));
        ZonedDateTime zonedDateTime2 = dateTime.atZone(romeZone);
        System.out.println("zonedDateTime2 = " + zonedDateTime2);

        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime3 = instant.atZone(romeZone);
        System.out.println("zonedDateTime3 = " + zonedDateTime3);

        LocalDateTime now2 = LocalDateTime.now(); // zone과 상관없이 os의 local 시간 값만을 가져옴.
        ZonedDateTime zonedDateTime4 = now2.atZone(romeZone); // LocalDateTime에는 zone값이 없음.
        System.out.println("zonedDateTime4 = " + zonedDateTime4);
        System.out.println("zonedDateTime4.withZoneSameInstant(ZoneId.of(\"Asia/Seoul\")) = " + zonedDateTime4.withZoneSameInstant(ZoneId.of("Asia/Seoul")));
        System.out.println("zonedDateTime4.withZoneSameLocal(ZoneId.of(\"Asia/Seoul\")) = " + zonedDateTime4.withZoneSameLocal(ZoneId.of("Asia/Seoul")));

        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println("zoneId = " + zoneId);
    }

    @Test
    public void changeFromLocalDateTimeToInstant() {
        Instant instant = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant();
        System.out.println("instant = " + instant);
        System.out.println("Instant.now() = " + Instant.now());// 그리니치 시간대 기준으로 출력

        LocalDateTime localDateTime = LocalDateTime.of(2014, 6, 12, 0, 0, 1);
        Instant instant1 = localDateTime.atZone(ZoneId.of("Europe/Rome")).toInstant(); // rome GMT+2 -> 2시간 뺀 시간 출력
        System.out.println("instant1 = " + instant1);

        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
    }

    @Test
    public void zoneOffsetTest() {
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00"); // 서머타임을 제대로 처리할 수 없으므로 권장하지 않는 방식

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
        System.out.println("dateTimeInNewYork = " + dateTimeInNewYork);

        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime zonedDateTime = Instant.now().atZone(newYorkZone);
        System.out.println("zonedDateTime = " + zonedDateTime);

        ZonedDateTime marchOneAtNewYork = LocalDateTime.of(2024, 3, 11, 0, 0, 0).atZone(newYorkZone);
        System.out.println("marchOneAtNewYork = " + marchOneAtNewYork);

        // 미국은 3월 두번째 일요일부터 서머타임 시작, 11월 첫번째 일요일 해제
        System.out.println("marchOneAtNewYork.minusDays(1) = " + marchOneAtNewYork.minusDays(1));// 하루가 빠지자만 23시간 차이
        System.out.println("marchOneAtNewYork.minusHours(24) = " + marchOneAtNewYork.minusHours(24));

    }

    @Test
    public void japanesDateTest() {
        JapaneseDate japaneseDate = JapaneseDate.from(LocalDate.now());
        System.out.println("japaneseDate = " + japaneseDate);

        Chronology japanesChronology = Chronology.ofLocale(Locale.JAPAN);
        System.out.println("japanesChronology.dateNow() = " + japanesChronology.dateNow());
    }
}
