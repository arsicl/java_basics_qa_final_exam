package com.griddynamics.student.courses;

public class AssigningCoursesToStudents {

    public static void assignJavaCourses(StudentCourses studentCourses){
        studentCourses.setCourseDuration(CoursesDuration.JAVA.getName(), CoursesDuration.JAVA.getDuration());
        studentCourses.setCourseDuration(CoursesDuration.JDBC.getName(), CoursesDuration.JDBC.getDuration());
        studentCourses.setCourseDuration(CoursesDuration.SPRING.getName(), CoursesDuration.SPRING.getDuration());
    }

    public static void assignJ2EECourses(StudentCourses studentCourses){
        studentCourses.setCourseDuration(CoursesDuration.TEST_DESIGN.getName(), CoursesDuration.TEST_DESIGN.getDuration());
        studentCourses.setCourseDuration(CoursesDuration.PAGE_OBJECT.getName(), CoursesDuration.PAGE_OBJECT.getDuration());
        studentCourses.setCourseDuration(CoursesDuration.SELENIUM.getName(), CoursesDuration.SELENIUM.getDuration());
    }
}
