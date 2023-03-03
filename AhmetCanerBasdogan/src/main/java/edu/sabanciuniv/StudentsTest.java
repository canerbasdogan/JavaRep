package edu.sabanciuniv;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class StudentsTest {
    public static void main(String[] args) {
        Students student1 = new Students("Male","Ahmet Caner","Başdoğan","Ümraniye","21/06/1987");
        Students student2 = new Students("Male","Kerem","Tuna","Ataşehir","17/03/1986");
        Students student3 = new Students("Female","Seda","Başdoğan","Sancaktepe","27/05/1990");

        List<Students> studentsList = new ArrayList<>();

        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        for (Students student : studentsList) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }

    }
}
