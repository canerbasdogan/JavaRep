package test;

import edu.sabanciuniv.School;
import edu.sabanciuniv.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class TestStudent {

    public static void main(String[] args) {

        Student student1 = new Student("Male","Ahmet Caner", "Başdoğan", "Ümraniye", "21/06/1987",1593577);
        Student student2 = new Student("Male","Aziz Kerem", "Tuna", "Ataşehir", "17/03/1986",2593587);
        Student student3 = new Student("Male","Fehmi Erdem", "Aybulut", "Maltepe", "27/03/1988",3938921);
        Student student4 = new Student("Female","Seda", "Başdoğan", "Sancaktepe", "27/05/1990",1598902);
        Student student5 = new Student("Male","Berke", "Taşkın", "Sancaktepe", "01/08/2022",5778946);

        School school1 = new School("İTÜ", "University", "0212 545 54 54","info@itu.edu.tr");
        School school2 = new School("YTÜ", "University", "0212 464 74 34","info@ytu.edu.tr");
        School school3 = new School("Bilfen", "High School", "0216 262 41 41","info@bilfen.edu.tr");

        student1.setSchool(school1);
        student2.setSchool(school1);
        student4.setSchool(school1);
        student3.setSchool(school2);
        student4.setSchool(school3);

        List<Student> studentList = new ArrayList();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);

        List<School> schoolList = new ArrayList<>();
        schoolList.add(school1);
        schoolList.add(school2);
        schoolList.add(school3);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        //saveSchools(schoolList, entityManager);
        //saveStudents(studentList, entityManager);
        //findAllStudents(entityManager);
        //deleteStudent(entityManager,student1);
        updateStudentAddress(entityManager,student3,"Taşdelen");

    }

    private static void saveStudents(List<Student> studentList,EntityManager entityManager){

        for (Student student : studentList) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }

    }

    private static void saveSchools(List<School> schoolList,EntityManager entityManager){

        for (School school: schoolList) {
            entityManager.getTransaction().begin();
            entityManager.persist(school);
            entityManager.getTransaction().commit();
        }

    }

    private static void findAllStudents(EntityManager entityManager){

        TypedQuery studentJpql = entityManager.createQuery("FROM Student s", Student.class);
        List<Student> studentList = studentJpql.getResultList();

        for (Student student: studentList){
            System.out.println(student);
        }

    }

    private static void deleteStudent(EntityManager entityManager, Student student){

        entityManager.getTransaction().begin();
        Student foundStudent = entityManager.createQuery("SELECT s FROM Student s WHERE " +
                "s.tcIdentityNumber = :tcNo",Student.class).setParameter("tcNo", student.getTcIdentityNumber())
                .getSingleResult();
        entityManager.remove(foundStudent);
        entityManager.getTransaction().commit();
        System.out.println(student + " deleted");

    }

    private static void updateStudentAddress(EntityManager entityManager, Student student, String newAddress){

        entityManager.getTransaction().begin();
        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.address = :stAdr"
                , Student.class).setParameter("stAdr",student.getAddress()).getSingleResult();
        foundStudent.setAddress(newAddress);
        entityManager.merge(foundStudent);
        entityManager.getTransaction().commit();
        System.out.println(student + " updated");
        
    }

}
