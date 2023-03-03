package edu.sabanciuniv;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="STUDENTS_TABLE")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Student_ID")
    private int studentID;
    private String gender;
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;
    private long tcIdentityNumber;

    @ManyToOne
    private School school;

    public Student(String gender, String firstName, String lastName, String address, String birthDate, long tcIdentityNumber) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.tcIdentityNumber = tcIdentityNumber;

    }

    public Student(){

    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public long getTcIdentityNumber() {
        return tcIdentityNumber;
    }

    public void setTcIdentityNumber(long tcIdentityNumber) {
        this.tcIdentityNumber = tcIdentityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return tcIdentityNumber == student.tcIdentityNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tcIdentityNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", tcIdentityNumber=" + tcIdentityNumber +
                '}';
    }
}
