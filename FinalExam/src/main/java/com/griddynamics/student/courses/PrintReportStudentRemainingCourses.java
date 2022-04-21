package com.griddynamics.student.courses;

import com.griddynamics.student.courses.generatingReports.GeneratingShortAndLongReportsForRemainingStudentCourses;

public class PrintReportStudentRemainingCourses {

    public static void main(String[] args) {
        StudentCourses javaStudent = new StudentCourses("Ivanov Ivan", "Java Developer");
        StudentCourses j2eeStudent = new StudentCourses("Sidorov Ivan", "J2EE Developer");

        AssigningCoursesToStudents.assignJavaCourses(javaStudent);
        AssigningCoursesToStudents.assignJ2EECourses(j2eeStudent);

        StudentCourses students[] = new StudentCourses[2];
        students[0] = javaStudent;
        students[1] = j2eeStudent;

        GeneratingShortAndLongReportsForRemainingStudentCourses generateReportShort =
                new GeneratingShortAndLongReportsForRemainingStudentCourses(students);

        GeneratingShortAndLongReportsForRemainingStudentCourses generateReportFull =
                new GeneratingShortAndLongReportsForRemainingStudentCourses(students, 1);
    }
}
