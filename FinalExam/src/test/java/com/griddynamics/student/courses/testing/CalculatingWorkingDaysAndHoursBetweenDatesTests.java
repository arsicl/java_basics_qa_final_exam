package com.griddynamics.student.courses.testing;

import com.griddynamics.student.courses.calculatingDuration.CalculatingWorkingDaysAndHoursBetweenDates;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatingWorkingDaysAndHoursBetweenDatesTests {
    @Test
    public void verifyExceptionWhenStartDateIsGreaterThanEndDate(){
        // given
        LocalDateTime startDate = (LocalDateTime.now()).plusDays(1);
        LocalDateTime endDate = LocalDateTime.now();

        // when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                    CalculatingWorkingDaysAndHoursBetweenDates.getBusinessDaysBetweenDays(startDate, endDate));

        // then
        assertEquals("Start date " + startDate
                    + " must be earlier than end date: " + endDate, ex.getMessage());
    }
}
