package com.griddynamics.student.courses;

public enum CoursesDuration {
    JAVA("Java", 16),
    JDBC("JDBC", 24),
    SPRING("Spring", 16),
    TEST_DESIGN("Test design",10),
    PAGE_OBJECT("Page Object",16),
    SELENIUM("Selenium", 16);

    private Integer duration;
    private String name;

    CoursesDuration(String name, Integer duration){
        this.name = name;
        this.duration = duration;
    }

    public String getName(){
        return name;
    }
    public Integer getDuration(){
        return  duration;
    }
}
