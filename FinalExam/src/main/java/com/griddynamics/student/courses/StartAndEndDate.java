package com.griddynamics.student.courses;

import java.time.LocalDateTime;

public class StartAndEndDate {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public StartAndEndDate(LocalDateTime startDate){
        this.startDate = startDate;
        this.endDate = LocalDateTime.now();
    }

    public StartAndEndDate(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate(){
        return this.endDate;
    }

    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
}
