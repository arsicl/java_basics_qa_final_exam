package com.griddynamics.student.courses;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentCourses {
    private String studentName;
    private String studentTrainingProgram;
    private Map<String, Integer> courses;

    public StudentCourses(String studentName, String studentTrainingProgram){
        this.studentName = studentName;
        this.studentTrainingProgram = studentTrainingProgram;
        this.courses = new LinkedHashMap<String, Integer>();
    }

    public Integer getCourseDuration(String key){
        return this.courses.get(key);
    }

    public void setCourseDuration(String key, Integer value){
        this.courses.put(key, value);
    }

    public String getStudentName(){
        return this.studentName;
    }

    public String getStudentTrainingProgram(){
        return this.studentTrainingProgram;
    }

    public Map<String, Integer> getAllStudentCourses(){
        return this.courses;
    }
}
