package com.crio.xcompany.company;

public class Employee {
    private String name;
    private Gender gender;
    private Employee manager; // Add manager field

    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.manager = null; // Initially no manager assigned
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    // Setter method for manager
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    // Getter method for manager
    public Employee getManager() {
        return manager;
    }

    // Override toString method to print Employee details
    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }
}