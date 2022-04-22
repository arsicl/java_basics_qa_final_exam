package com.griddynamics.student.courses.generatingReports;

import com.griddynamics.student.courses.StartAndEndDate;
import com.griddynamics.student.courses.StudentCourses;
import com.griddynamics.student.courses.calculatingDuration.CalculatingCourseDurationInWorkingDays;
import com.griddynamics.student.courses.calculatingDuration.CalculatingRemainingDaysAndHoursOfTraining;
import com.griddynamics.student.courses.generatingReports.EnteringDateOfCourseStart;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class GeneratingShortAndLongReportsForRemainingStudentCourses {
    private StudentCourses studentCourses[];
    private StudentCourses studentTrainingParticularStudent;
    private CalculatingRemainingDaysAndHoursOfTraining remainingDaysAndHoursOfCourseParticularStudent;
    private CalculatingRemainingDaysAndHoursOfTraining[] remainingDaysAndHoursOfTraining;
    private LocalDateTime inputDateTime;
    private StartAndEndDate startEndDate;

    public GeneratingShortAndLongReportsForRemainingStudentCourses(StudentCourses[] studentCourses){
        EnteringDateOfCourseStart.enterInputDate();
        this.inputDateTime = EnteringDateOfCourseStart.getDateInput();
        this.startEndDate = new StartAndEndDate(this.inputDateTime);
        this.generatingHeaderReportWithCurrentDate();
        this.studentCourses = studentCourses;
        for(StudentCourses student: this.studentCourses){
            showShortReport(student);
            System.out.println("-----------------------------------------------------------------------------------");
        }
    }

    public GeneratingShortAndLongReportsForRemainingStudentCourses(StudentCourses[] studentCourses, int inputParameterForReportType){
        EnteringDateOfCourseStart.enterInputDate();
        this.inputDateTime = EnteringDateOfCourseStart.getDateInput();
        this.startEndDate = new StartAndEndDate(this.inputDateTime);
        this.generatingHeaderReportWithCurrentDate();
        this.studentCourses = studentCourses;

        if(inputParameterForReportType == 0){
            for(StudentCourses student: this.studentCourses){
                showShortReport(student);
                System.out.println("-----------------------------------------------------------------------------------");
            }
        }
        else{
            for(StudentCourses student: this.studentCourses){
                showFullReport(student);
                System.out.println("-----------------------------------------------------------------------------------");
            }
        }
        
    }

    private void generatingHeaderReportWithCurrentDate(){
        StringBuilder shortReport = new StringBuilder();
        shortReport = shortReport.append("Generating report date - ");
        shortReport = shortReport.append( new SimpleDateFormat("dd MMMM, EEEE, HH:MM").format(new Date()) + ": \n");

    }
    private void showShortReport(StudentCourses studentCourses) {
        //Short (Generating report date - 8 June 2020, Monday, 15:00) :
        //Ivanov Ivan (Java Developer) - Training is not finished. 1 d 3 hours are left until the end.
        //Training completed. 3 hours have passed since the end.
        this.studentTrainingParticularStudent = studentCourses;
        this.remainingDaysAndHoursOfCourseParticularStudent = new CalculatingRemainingDaysAndHoursOfTraining(this.studentTrainingParticularStudent, this.startEndDate);

        StringBuilder shortReport = new StringBuilder();
        shortReport.append(studentTrainingParticularStudent.getStudentName() + " (");
        shortReport.append(studentTrainingParticularStudent.getStudentTrainingProgram() + ") ");

        if(this.remainingDaysAndHoursOfCourseParticularStudent.areAllCoursesFinished()){
            shortReport.append("Training completed. ");
            if(this.remainingDaysAndHoursOfCourseParticularStudent.getDaysFromTheLastCourse() > 0){
               shortReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getDaysFromTheLastCourse());
               shortReport.append(" d ");
            }
            shortReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getHoursFromTheLastCourse());
            shortReport.append(" hours have passed since the end.");
        }
        else if(!this.remainingDaysAndHoursOfCourseParticularStudent.areAllCoursesFinished()){
            shortReport.append("Training is not finished. ");
            if(this.remainingDaysAndHoursOfCourseParticularStudent.getLeftDays() > 0){
                shortReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getLeftDays());
                shortReport.append(" d ");
            }
            shortReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getLeftHours());
            shortReport.append(" hours are left until the end.");
        }

        System.out.println(shortReport);
    }

    private void showFullReport(StudentCourses studentCourses) {
        this.studentTrainingParticularStudent = studentCourses;
        this.remainingDaysAndHoursOfCourseParticularStudent = new CalculatingRemainingDaysAndHoursOfTraining(this.studentTrainingParticularStudent, this.startEndDate);
        CalculatingCourseDurationInWorkingDays calculatingCourseDurationInWorkingDays = new CalculatingCourseDurationInWorkingDays(this.studentTrainingParticularStudent);

        StringBuilder fullReport = new StringBuilder();
        fullReport.append("Student name: ");
        fullReport.append(studentTrainingParticularStudent.getStudentName() + " \n");

        fullReport.append("Working time (from 10:00 to 18:00h)" + "\n");

        fullReport.append("Program name: ");
        fullReport.append(studentTrainingParticularStudent.getStudentTrainingProgram() + "\n");

        fullReport.append("Program duration: ");
        fullReport.append(calculatingCourseDurationInWorkingDays.getCourseDurationInDays() + " days, ");
        fullReport.append(calculatingCourseDurationInWorkingDays.getCourseDurationInHours() + " hours \n");

        fullReport.append("Start date: ");
        fullReport.append(new SimpleDateFormat("dd MMMM, EEEE, HH:MM").format(new Date()) + ": \n");

        fullReport.append("End date: ");
        LocalDateTime endDate = (LocalDateTime.now()).plusDays(calculatingCourseDurationInWorkingDays.getCourseDurationInDays());
                endDate = endDate.plusHours(calculatingCourseDurationInWorkingDays.getCourseDurationInHours());

        fullReport.append(endDate + "\n");

        if(this.remainingDaysAndHoursOfCourseParticularStudent.areAllCoursesFinished()){
            fullReport.append("Training completed. ");
            if(this.remainingDaysAndHoursOfCourseParticularStudent.getDaysFromTheLastCourse() > 0){
                fullReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getDaysFromTheLastCourse());
                fullReport.append(" d ");
            }
            fullReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getHoursFromTheLastCourse());
            fullReport.append(" hours have passed since the end.");
        }
        else if(!this.remainingDaysAndHoursOfCourseParticularStudent.areAllCoursesFinished()){
            fullReport.append("Training is not finished. ");
            if(this.remainingDaysAndHoursOfCourseParticularStudent.getLeftDays() > 0){
                fullReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getLeftDays());
                fullReport.append(" d ");
            }
            fullReport.append(this.remainingDaysAndHoursOfCourseParticularStudent.getLeftHours());
            fullReport.append(" hours are left until the end.");
        }

        System.out.println(fullReport);
    }
}
