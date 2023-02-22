package edu.sabanciuniv.test;

import edu.sabanciuniv.model.*;
import edu.sabanciuniv.utility.EntityManagerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.time.Month;

public class TestSchoolManagementSystem {

    public static void main(String[] args) {
        saveTestData();

    }

    private static void saveTestData(){

        Student student1 = new Student("Caner Başdoğan",(LocalDate.of(1987, Month.JUNE,21)),
                "Ümraniye","Male");
        Student student2 = new Student("Seda Başdoğan",(LocalDate.of(1990, Month.MAY,27)),
                "Sancaktepe","Female");
        Student student3 = new Student("Sümeyye Başdoğan",(LocalDate.of(1991, Month.SEPTEMBER,6)),
                "Ümraniye","Female");

        Course course1 = new Course("Java","101","12");
        Course course2 = new Course("Python","102","10");
        Course course3 = new Course("Machine Learning","103","9");

        Instructor visitingResearcher1 = new VisitingResearcher("Uğur Taşkın","Beykoz",5514944949L,200);
        Instructor visitingResearcher2 = new VisitingResearcher("Sabiha Başdoğan","Çekmeköy",551462449L,300);
        Instructor permanentInstructor1 = new PermanentInstructor("Sait Başdoğan","Çekmeköy",5057146432L,42000);

        course1.setInstructor(visitingResearcher1);
        course2.setInstructor(permanentInstructor1);
        course3.setInstructor(visitingResearcher2);

        student1.getCourseList().add(course1);
        student1.getCourseList().add(course2);
        student2.getCourseList().add(course1);
        student2.getCourseList().add(course2);
        student2.getCourseList().add(course3);
        student3.getCourseList().add(course2);
        student3.getCourseList().add(course3);

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(visitingResearcher1);
            entityManager.persist(visitingResearcher2);
            entityManager.persist(permanentInstructor1);

            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);

            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);

            entityManager.getTransaction().commit();
            System.out.println("All data persisted...");

        } catch (Exception e){
            entityManager.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(entityManager);

        }


    }


}
