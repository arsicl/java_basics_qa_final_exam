package com.griddynamics.student.courses.calculatingDuration;

import com.griddynamics.student.courses.StudentCourses;

public class CalculatingCourseDurationInWorkingDays {
    private int durationInDays;
    private int durationInHours;
    private StudentCourses studentCourses;

    public CalculatingCourseDurationInWorkingDays(StudentCourses studentCourses){
        this.studentCourses = studentCourses;
        this.calculatingTrainingDuration();
    }

    public void calculatingTrainingDuration(){
        Integer sum = 0;

        for(Integer courseDuration: this.studentCourses.getAllStudentCourses().values()){
            sum += courseDuration;
        }

        // Working days are 8 hours, between 10-18:00h
        this.durationInDays = sum / 8;
        this.durationInHours = sum % 8;
    }

    public int getCourseDurationInDays(){
        return this.durationInDays;
    }

    public int getCourseDurationInHours(){
        return this.durationInHours;
    }
}
