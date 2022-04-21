package com.griddynamics.student.courses.testing;

import com.griddynamics.student.courses.AssigningCoursesToStudents;
import com.griddynamics.student.courses.calculatingDuration.CalculatingCourseDurationInWorkingDays;
import com.griddynamics.student.courses.CoursesDuration;
import com.griddynamics.student.courses.StudentCourses;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatingCourseDurationInWorkingDaysTests {
    @Test
    public void areWorkingDaysOfCourseDurationCalculatedProperly(){
        // given
        StudentCourses javaStudent = new StudentCourses("Ivanov Ivan", "Java Developer");
        AssigningCoursesToStudents.assignJavaCourses(javaStudent);
        CalculatingCourseDurationInWorkingDays durationInWorkingDays = new CalculatingCourseDurationInWorkingDays(javaStudent);

        // when
        int gottenDurationInWorkingDays = durationInWorkingDays.getCourseDurationInDays();

        // then
        // working day is 8 hours, 5 days in week
        // java courses in sum are 56 hours long, i.e. 7 days and 0 hours
        int expectedValue = (CoursesDuration.JAVA.getDuration() + CoursesDuration.JDBC.getDuration()
                            + CoursesDuration.SPRING.getDuration()) / 8;
        assertEquals(expectedValue, gottenDurationInWorkingDays);
    }

    @Test
    public void areWorkingHoursOfCourseDurationCalculatedProperly(){
        // given
        StudentCourses j2eeStudent = new StudentCourses("Sidorov Ivan", "J2EE Developer");
        AssigningCoursesToStudents.assignJ2EECourses(j2eeStudent);
        CalculatingCourseDurationInWorkingDays durationInWorkingHours = new CalculatingCourseDurationInWorkingDays(j2eeStudent);

        // when
        int gottenDurationInWorkingHours = durationInWorkingHours.getCourseDurationInHours();

        // then
        // working day is 8 hours, 5 days in week
        // j2ee courses in sum are 42 hours long, i.e. 5 days and 2 hours
        int expectedValue = (CoursesDuration.PAGE_OBJECT.getDuration() + CoursesDuration.TEST_DESIGN.getDuration()
                + CoursesDuration.SELENIUM.getDuration()) % 8;
        assertEquals(expectedValue, gottenDurationInWorkingHours);
    }
}
