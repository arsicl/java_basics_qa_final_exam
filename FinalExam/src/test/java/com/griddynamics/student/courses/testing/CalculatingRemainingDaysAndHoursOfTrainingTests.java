package com.griddynamics.student.courses.testing;

import com.griddynamics.student.courses.AssigningCoursesToStudents;
import com.griddynamics.student.courses.calculatingDuration.CalculatingCourseDurationInWorkingDays;
import com.griddynamics.student.courses.calculatingDuration.CalculatingRemainingDaysAndHoursOfTraining;
import com.griddynamics.student.courses.StudentCourses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatingRemainingDaysAndHoursOfTrainingTests {
    private static StudentCourses javaStudent;
    private static CalculatingCourseDurationInWorkingDays durationInWorkingDays;

    @BeforeEach
    public void createStudent(){
        javaStudent = new StudentCourses("Ivanov Ivan", "Java Developer");
        AssigningCoursesToStudents.assignJavaCourses(javaStudent);
        durationInWorkingDays = new CalculatingCourseDurationInWorkingDays(javaStudent);
    }

    @Test
    public void verifyLeftHoursAndDaysInCourse(){
        // given
        // subtracted fewer days than it takes for  java courses to be finished
        LocalDateTime startDate = (LocalDateTime.now()).minusDays(durationInWorkingDays.getCourseDurationInDays() - 3);
        CalculatingRemainingDaysAndHoursOfTraining calculatingRemainingDaysAndHoursOfTraining =
                new CalculatingRemainingDaysAndHoursOfTraining(javaStudent, startDate);

        // when
        boolean areCoursesFinished = calculatingRemainingDaysAndHoursOfTraining.areAllCoursesFinished();
        int leftDays = calculatingRemainingDaysAndHoursOfTraining.getLeftDays();

        // then
        // at least (durationOfCourse (7) - 3) 4 days, if some of the days were weekends, it will be more than 4
        assertTrue(leftDays >= 4);
    }

    @Test
    public void checkIfCourseIsFinishedWhenDurationIsBiggerThanCourseDuration(){
        // given
        // subtracted more days than it takes for  java courses to be finished
        LocalDateTime startDate = (LocalDateTime.now()).minusDays(durationInWorkingDays.getCourseDurationInDays() + 3);
        CalculatingRemainingDaysAndHoursOfTraining calculatingRemainingDaysAndHoursOfTraining =
                new CalculatingRemainingDaysAndHoursOfTraining(javaStudent, startDate);

        // when
        boolean areCoursesFinished = calculatingRemainingDaysAndHoursOfTraining.areAllCoursesFinished();
        int daysBeforeLastCourse = calculatingRemainingDaysAndHoursOfTraining.getDaysFromTheLastCourse();

        // then
        // since java courses lasts 7 working days, they should be finished
        assertTrue(areCoursesFinished);
        assertTrue(daysBeforeLastCourse >= 1);
    }
}
