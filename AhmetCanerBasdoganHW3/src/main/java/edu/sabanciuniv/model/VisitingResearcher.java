package edu.sabanciuniv.model;

import jakarta.persistence.Entity;

@Entity
public class VisitingResearcher extends Instructor{

    private int hourlySalary;
    public VisitingResearcher(String name, String address, long phoneNumber, int hourlySalary) {
        super(name, address, phoneNumber);
        this.hourlySalary = hourlySalary;
    }

    public VisitingResearcher(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public VisitingResearcher() {
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    @Override
    public String toString() {
        return "VisitingResearcher{" +
                "hourlySalary=" + hourlySalary +
                '}';
    }
}
