package com.griddynamics.student.courses.testing;

import com.griddynamics.student.courses.calculatingDuration.CalculatingWorkingDaysAndHoursBetweenDates;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatingWorkingDaysAndHoursBetweenDatesTests {
    private static LocalDateTime startDate;
    private static LocalDateTime endDate;

    @Test
    public void verifyExceptionWhenStartDateIsGreaterThanEndDate(){
        // given
        startDate = (LocalDateTime.now()).plusDays(1);
        endDate = LocalDateTime.now();

        // when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                    CalculatingWorkingDaysAndHoursBetweenDates.getBusinessDaysBetweenDays(startDate, endDate));

        // then
        assertEquals("Start date " + startDate
                    + " must be earlier than end date: " + endDate, ex.getMessage());
    }

    @Test
    public void verifyWorkingDaysAndHoursAreCalculatedProperly(){
        // given
        String startDateString = "2020-06-01T08:00:00";;
        String endDateString = "2020-06-08T15:00:00";
        startDate = LocalDateTime.parse(startDateString);
        endDate = LocalDateTime.parse(endDateString);
        Duration duration = Duration.between(startDate, endDate);

        long expectedDays = duration.toDays() - 2; // subtracted weekend days
        // workingDays are without start and end date,
        // where there are 8 hours in start date and 5 hours in end date (10:00h - 15:00h),
        // hours are not in sum, just remaining working hours without working days
        long expectedHours = (expectedDays * 8 + 8 + 5) % 8;

        // when
        int workingDays = CalculatingWorkingDaysAndHoursBetweenDates.getBusinessDaysBetweenDays(startDate, endDate);
        int workingHours = CalculatingWorkingDaysAndHoursBetweenDates.getWorkingHoursBetweenDates(startDate, endDate);

        // then
        assertEquals(expectedDays, workingDays);
        assertEquals(expectedHours, workingHours);
    }
}
