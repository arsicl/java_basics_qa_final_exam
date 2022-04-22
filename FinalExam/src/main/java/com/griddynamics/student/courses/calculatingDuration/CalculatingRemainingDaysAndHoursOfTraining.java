package com.griddynamics.student.courses.calculatingDuration;

import com.griddynamics.student.courses.StartAndEndDate;
import com.griddynamics.student.courses.StudentCourses;

import java.time.LocalDateTime;

public class CalculatingRemainingDaysAndHoursOfTraining {
    private StudentCourses studentCourses;
    private LocalDateTime inputDateTime;
    private LocalDateTime endDate;
    private CalculatingCourseDurationInWorkingDays calculatingCourseDurationInWorkingDays;
    private int leftDays;
    private int leftHours;
    private int daysFromTheLastCourse;
    private int hoursFromTheLastCourse;

    public CalculatingRemainingDaysAndHoursOfTraining(StudentCourses studentCourses, StartAndEndDate startEndDate){
        this.inputDateTime = startEndDate.getStartDate();
        this.endDate = startEndDate.getEndDate();
        this.studentCourses = studentCourses;
        this.calculatingCourseDurationInWorkingDays = new CalculatingCourseDurationInWorkingDays(this.studentCourses);
    }

    public boolean areAllCoursesFinished(){
        int datesBetweenStartAndCurrent = CalculatingWorkingDaysAndHoursBetweenDates.
                getBusinessDaysBetweenDays(this.inputDateTime, this.endDate);
        int hoursBetweenStartAndCurrent = CalculatingWorkingDaysAndHoursBetweenDates.
                getWorkingHoursBetweenDates(this.inputDateTime, this.endDate);
        int workingHoursBetweenStartAndCurrent = datesBetweenStartAndCurrent * 8 + hoursBetweenStartAndCurrent;

        int durationOfCoursesInDays = this.calculatingCourseDurationInWorkingDays.getCourseDurationInDays();
        int durationOfCoursesInHours = this.calculatingCourseDurationInWorkingDays.getCourseDurationInHours();
        int remainingCoursesInHours = durationOfCoursesInDays * 8 + durationOfCoursesInHours;

        if(remainingCoursesInHours > workingHoursBetweenStartAndCurrent){
            calculateHoursAndDaysLeft(remainingCoursesInHours, workingHoursBetweenStartAndCurrent);
            return false;
        }
        else{
            calculateDaysAndHoursSinceLastCourse(remainingCoursesInHours, workingHoursBetweenStartAndCurrent);
            return true;
        }
    }

    private void calculateDaysAndHoursSinceLastCourse(int remainingCoursesInHours, int workingHoursBetweenStartAndCurrent) {
        this.hoursFromTheLastCourse = workingHoursBetweenStartAndCurrent - remainingCoursesInHours;
        this.daysFromTheLastCourse = this.hoursFromTheLastCourse / 8;
        this.hoursFromTheLastCourse = this.hoursFromTheLastCourse % 8;
    }

    private void calculateHoursAndDaysLeft(int remainingCoursesInHours, int workingHoursBetweenStartAndCurrent) {
        this.leftHours = remainingCoursesInHours - workingHoursBetweenStartAndCurrent;
        this.leftDays = this.leftHours / 8;
        this.leftHours = this.leftHours % 8;
    }

    public int getLeftDays(){
        return this.leftDays;
    }

    public int getLeftHours() {
        return this.leftHours;
    }

    public int getDaysFromTheLastCourse(){
        return this.daysFromTheLastCourse;
    }

    public int getHoursFromTheLastCourse(){
        return this.hoursFromTheLastCourse;
    }
}
