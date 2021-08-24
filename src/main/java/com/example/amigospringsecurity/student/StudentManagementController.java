package com.example.amigospringsecurity.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
@EnableGlobalMethodSecurity(prePostEnabled = true) //to do auth by permision in the controller
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "Olaleye Oluwatosin Stephenn")
            ,new Student(2,"Adebayo Oluwaseun")
            ,new Student(3, "Adejoke Yemi"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getStudents()
    {
       return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void createStudent(@RequestBody Student student){
        System.out.println(student);
        System.out.println("Student Created");

    }

    @DeleteMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(studentId);
        System.out.println("Student Deleted");
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println(String.format("%s %s",student, studentId));
        System.out.println("Student Updated");
    }
}
