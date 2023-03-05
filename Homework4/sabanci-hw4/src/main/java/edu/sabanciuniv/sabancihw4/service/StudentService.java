package edu.sabanciuniv.sabancihw4.service;

import edu.sabanciuniv.sabancihw4.model.Student;
import edu.sabanciuniv.sabancihw4.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService  {

    @Autowired
    private StudentRepository studentRepository;

    public Student addNewStudent(Student student){

        return studentRepository.save(student);

    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();

    }


    public Student updateExistingStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteExistingStudent(Student student) {
        studentRepository.delete(student);
    }
}
