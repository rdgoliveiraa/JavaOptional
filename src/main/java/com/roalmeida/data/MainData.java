package com.roalmeida.data;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class MainData {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        localDate = LocalDate.of(2015, 02, 20);
        System.out.println(localDate);

        localDate = LocalDate.parse("2015-02-20");
        System.out.println(localDate);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println(tomorrow);

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println(previousMonthSameDay);

        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
        System.out.println(sunday);

        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
        System.out.println(twelve);

        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println(leapYear);

        boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
        System.out.println("notBefore:" + notBefore);

        boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.parse("2016-06-11"));
        System.out.println("isAfter:" + isAfter);

        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        System.out.println(beginningOfDay);
        LocalDate firstDayOfMounth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMounth);

        //LocalTime represents time without a date
        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime sixThirty = LocalTime.parse("06:30");
        System.out.println(sixThirty);

        LocalTime sixThirtyOf = LocalTime.of(6, 30);
        System.out.println(sixThirtyOf);

        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        System.out.println(sevenThirty);

        int six = LocalTime.parse("06:30").getHour();
        System.out.println(six);

        boolean isBefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        System.out.println(isBefore);

        LocalTime maxTime = LocalTime.MAX;
        System.out.println(maxTime);

        // Working With LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30));

        System.out.println(LocalDateTime.parse("2015-02-20T06:30:00"));

        System.out.println(localDateTime.plusDays(1));

        System.out.println(localDateTime.minusHours(2));

        // Using ZonedDateTime API
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        ZoneOffset offset = ZoneOffset.of("+03:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime1, offset);
        System.out.println(offset.getRules());

        // Using Period and Duration
        // Period representa qauntidade de tempo em anos, meses e dias
        LocalDate initialDate = LocalDate.parse("2007-05-10");
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        System.out.println("Periodo Inicial:" + initialDate + " - Periodo Final:" + finalDate);

        int fivePeriod = Period.between(initialDate, finalDate).getDays();
        System.out.println(fivePeriod);

        long fiveChronoUnit = ChronoUnit.DAYS.between(initialDate, finalDate);
        System.out.println(fiveChronoUnit);

        // Duration é usado para Time
        LocalTime initialTime = LocalTime.of(6, 30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        System.out.println(initialTime);
        System.out.println(finalTime);

        long thirty = Duration.between(initialTime, finalTime).getSeconds();
        long thirty1 = ChronoUnit.SECONDS.between(initialTime, finalTime);
        System.out.println(thirty);
        System.out.println(thirty1);

        // Compatibility With Date and Calendar
        LocalDateTime date = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println(date);

        LocalDateTime calendar = LocalDateTime.ofInstant(Calendar.getInstance().toInstant(), ZoneId.systemDefault());
        System.out.println(calendar);

        // The LocalDateTime can be constructed from epoch seconds. The result of the below code would be a LocalDateTime representing 2016-06-13T11:34:50
        LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);

        // Date and Time Formatting
        LocalDateTime localDateTime2 = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);
        String locaDateString = localDateTime2.format(DateTimeFormatter.ISO_DATE);
        System.out.println(locaDateString);

        System.out.println(localDateTime2.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        
        System.out.println(localDateTime2
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.UK)));

        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        System.out.println(zonedDateTime1
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.UK)));

        System.out.println(zonedDateTime1
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                        .withLocale(Locale.UK)));

        System.out.println(zonedDateTime1
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                        .withLocale(Locale.UK)));
    }
}