package com.latihan.demo.student;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class StudentService {
    public List<Student> getStudents(){
        return List.of(new Student(
                1L,
                "Jaelani",
                "j43lani@gmail.com",
                LocalDate.of(1990, Month.APRIL,15),
                30
        ));
    }
}
