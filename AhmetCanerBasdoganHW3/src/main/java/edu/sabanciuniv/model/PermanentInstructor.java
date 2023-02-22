package edu.sabanciuniv.model;

import jakarta.persistence.Entity;

@Entity
public class PermanentInstructor extends Instructor{

    private int fixedSalary;

    public PermanentInstructor(String name, String address, long phoneNumber, int fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public PermanentInstructor(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public PermanentInstructor() {
    }

    public int getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    @Override
    public String toString() {
        return "PermanentInstructor{" +
                "fixedSalary=" + fixedSalary +
                '}';
    }
}
