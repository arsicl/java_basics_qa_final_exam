package com.griddynamics.student.courses.calculatingDuration;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CalculatingWorkingDaysAndHoursBetweenDates {

    public static int getBusinessDaysBetweenDays(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate.isAfter(endDate)){
            String msg = "Start date " + startDate
                    + " must be earlier than end date: " + endDate;
            throw new IllegalArgumentException(msg);
        }

        int workingDays = 0;
        LocalDateTime daysBetween = startDate;

        // Calculating dates including start and end date
        while(daysBetween.isBefore(endDate)){
            if(isDayWorkingDay(daysBetween)){
                workingDays++;
            }
            daysBetween = daysBetween.plusDays(1);
        }

        // Subtract start and end date from the sum and add day if hours in start and end date exceeds 8h
        workingDays = (workingDays - 2) + getWorkingHoursInStartEndDateInSum(startDate, endDate) / 8;
        return  workingDays;
    }

    public static boolean isDayWorkingDay(LocalDateTime date){
        DayOfWeek dayInWeek = date.getDayOfWeek();
        if(dayInWeek != DayOfWeek.SATURDAY
                && dayInWeek != DayOfWeek.SUNDAY){
            return true;
        }
        else{
            return false;
        }
    }

    private static int getWorkingHoursInStartEndDateInSum(LocalDateTime startDate, LocalDateTime endDate){
        int startDateInHours = getWorkingHoursInStartDate(startDate);
        int endDateInHours = getWorkingHoursInEndDate(endDate);

        return  startDateInHours + endDateInHours;
    }


    private static int getWorkingHoursInStartDate(LocalDateTime startDate){
        int workingHours = 0;
        if(isDayWorkingDay(startDate)){
            int startDateInHours = startDate.getHour();
            if(startDateInHours < 18){
                startDateInHours = 18 - startDateInHours;
            }
            else{
                startDateInHours = 0;
            }

            if(startDateInHours > 8){
                startDateInHours = 8;
            }
            workingHours = startDateInHours;
        }
        return workingHours;
    }

    private static int getWorkingHoursInEndDate(LocalDateTime endDate){
        int workingHours = 0;
        if(isDayWorkingDay(endDate)){
            int endDateInHours = endDate.getHour();

            if(endDateInHours >= 18){
                endDateInHours = 8;
            }
            else if(endDateInHours > 10){
                endDateInHours = endDateInHours - 10;
            }
            else{
                endDateInHours = 0;
            }
            workingHours = endDateInHours;
        }
        return workingHours;
    }

    public static int getWorkingHoursBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
        int workingHoursBetweenDates = (getBusinessDaysBetweenDays(startDate, endDate) * 8
                                        + getWorkingHoursInStartEndDateInSum(startDate, endDate)) % 8;
        return workingHoursBetweenDates;
    }

}
