package com.griddynamics.student.courses.generatingReports;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class EnteringDateOfCourseStart {
    private static LocalDateTime dateInput;
    private static Scanner input = new Scanner(System.in);

    public static LocalDateTime parsingExceptionInInputDate() throws ParseException{
        System.out.println("Enter starting date in format YYYY-MM-ddTHH:mm:ss");
        String inputDateFromUser = input.next();
        dateInput = LocalDateTime.parse(inputDateFromUser);
        return dateInput;
    }

    public static void enterInputDate(){
        try{
            dateInput = parsingExceptionInInputDate();
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public static LocalDateTime getDateInput(){
        return dateInput;
    }
}
