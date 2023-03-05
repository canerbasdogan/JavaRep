package edu.sabanciuniv.sabancihw4.repository;

import edu.sabanciuniv.sabancihw4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


}
