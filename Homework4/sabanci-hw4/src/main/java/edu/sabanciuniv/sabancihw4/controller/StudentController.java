package edu.sabanciuniv.sabancihw4.controller;

import edu.sabanciuniv.sabancihw4.model.Student;
import edu.sabanciuniv.sabancihw4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();

    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);

    }

    @PutMapping ("/students")
    public Student updateExistingStudent(@RequestBody Student student){
        return studentService.updateExistingStudent(student);

    }

    @DeleteMapping ("/students")
    public void deleteExistingStudent(@RequestBody Student student){
        studentService.deleteExistingStudent(student);

    }

}
