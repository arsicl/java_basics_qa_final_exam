package com.griddynamics.student.courses.testing;

import com.griddynamics.student.courses.AssigningCoursesToStudents;
import com.griddynamics.student.courses.StartAndEndDate;
import com.griddynamics.student.courses.calculatingDuration.CalculatingCourseDurationInWorkingDays;
import com.griddynamics.student.courses.calculatingDuration.CalculatingRemainingDaysAndHoursOfTraining;
import com.griddynamics.student.courses.StudentCourses;
import com.griddynamics.student.courses.calculatingDuration.CalculatingWorkingDaysAndHoursBetweenDates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatingRemainingDaysAndHoursOfTrainingTests {
    private static StudentCourses javaStudent, j2eeStudent;
    private static CalculatingCourseDurationInWorkingDays durationInWorkingDaysJavaCourse;
    private static CalculatingCourseDurationInWorkingDays durationInWorkingDaysJ2EECourse;
    private static long workingHoursInSumBetweenDates;
    private static Duration duration;
    private static StartAndEndDate startEndDate;
    static LocalDateTime startDate;
    static LocalDateTime endDate;

    @BeforeEach
    public void createStudent(){
        String startDateString = "2020-06-01T08:00:00";;
        String endDateString = "2020-06-08T15:00:00";
        startDate = LocalDateTime.parse(startDateString);
        endDate = LocalDateTime.parse(endDateString);
        startEndDate = new StartAndEndDate(startDate, endDate);
        duration = Duration.between(startDate, endDate);
        // from days between weekend days are subtracted
        // and added 8 hours in start date and 5 hours in end date (10:00h - 15:00h),
        workingHoursInSumBetweenDates =  (duration.toDays() - 2) * 8 + 8 + 5;
    }

    @Test
    public void verifyLeftHoursAndDaysInCourse(){
        // given
        javaStudent = new StudentCourses("Ivanov Ivan", "Java Developer");
        AssigningCoursesToStudents.assignJavaCourses(javaStudent);
        durationInWorkingDaysJavaCourse = new CalculatingCourseDurationInWorkingDays(javaStudent);
        int durationInWorkingHoursJava = durationInWorkingDaysJavaCourse.getCourseDurationInHours();
        CalculatingRemainingDaysAndHoursOfTraining calculatingRemainingDaysAndHoursOfTraining =
                new CalculatingRemainingDaysAndHoursOfTraining(javaStudent, startEndDate);
        int durationInWorkingDays = durationInWorkingDaysJavaCourse.getCourseDurationInDays();

        // when
        boolean areCoursesFinished = calculatingRemainingDaysAndHoursOfTraining.areAllCoursesFinished();
        int leftDays = calculatingRemainingDaysAndHoursOfTraining.getLeftDays();
        int leftHours = calculatingRemainingDaysAndHoursOfTraining.getLeftHours();

        // then
        long expectedDays = durationInWorkingDays - (workingHoursInSumBetweenDates - durationInWorkingHoursJava) / 8;
        long expectedHours = ((durationInWorkingDays * 8 + durationInWorkingHoursJava) - workingHoursInSumBetweenDates)% 8;
        assertEquals(expectedDays, leftDays);
        assertEquals(expectedHours, leftHours);
    }

    @Test
    public void verifyDaysAndHoursAfterLastCourse(){
        // given
        j2eeStudent = new StudentCourses("Sidorov Ivan", "J2EE Developer");
        AssigningCoursesToStudents.assignJ2EECourses(j2eeStudent);
        durationInWorkingDaysJ2EECourse = new CalculatingCourseDurationInWorkingDays(j2eeStudent);;
        CalculatingRemainingDaysAndHoursOfTraining calculatingRemainingDaysAndHoursOfTraining =
                new CalculatingRemainingDaysAndHoursOfTraining(j2eeStudent, startEndDate);

        // when
        boolean areCoursesFinished = calculatingRemainingDaysAndHoursOfTraining.areAllCoursesFinished();
        int daysFromLastCourse = calculatingRemainingDaysAndHoursOfTraining.getDaysFromTheLastCourse();
        int hoursFromLastCourse = calculatingRemainingDaysAndHoursOfTraining.getHoursFromTheLastCourse();

        // then
        // from the last course 3 hours passed
        assertEquals(0, daysFromLastCourse);
        assertEquals(3, hoursFromLastCourse);
    }

    @Test
    public void checkIfCourseIsFinishedWhenDurationIsBiggerThanCourseDuration(){
        // given
        javaStudent = new StudentCourses("Ivanov Ivan", "Java Developer");
        AssigningCoursesToStudents.assignJavaCourses(javaStudent);
        durationInWorkingDaysJavaCourse = new CalculatingCourseDurationInWorkingDays(javaStudent);
        // subtracted more days than it takes for  java courses to be finished
        LocalDateTime startingDate = (LocalDateTime.now()).minusDays(durationInWorkingDaysJavaCourse.getCourseDurationInDays() + 3);
        startEndDate = new StartAndEndDate(startingDate);
        CalculatingRemainingDaysAndHoursOfTraining calculatingRemainingDaysAndHoursOfTraining =
                new CalculatingRemainingDaysAndHoursOfTraining(javaStudent, startEndDate);

        // when
        boolean areCoursesFinished = calculatingRemainingDaysAndHoursOfTraining.areAllCoursesFinished();

        // then
        // since java courses lasts 7 working days, they should be finished
        assertTrue(areCoursesFinished);
    }
}
