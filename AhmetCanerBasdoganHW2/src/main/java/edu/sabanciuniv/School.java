package edu.sabanciuniv;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="SCHOOLS_TABLE")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GOV_AG_CODE",nullable = false)
    private int governmentAgencyCode;
    private String schoolName;
    private String schoolType;
    private String phoneNumber;
    private String emailAddress;

    @OneToMany(mappedBy = "school")
    private List<Student> studentList = new ArrayList<>();

    public School(String schoolName, String schoolType, String phoneNumber, String emailAddress) {
        this.schoolName = schoolName;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public School(){}

    public int getGovernmentAgencyCode() {
        return governmentAgencyCode;
    }

    public void setGovernmentAgencyCode(int governmentAgencyCode) {
        this.governmentAgencyCode = governmentAgencyCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolName='" + schoolName + '\'' +
                ", schoolType='" + schoolType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
