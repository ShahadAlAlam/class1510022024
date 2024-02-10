package org.example;

public class Employee implements Comparable<Employee> {
    int empId;
    String name;

    public Employee() {
    }// getting the name of the employee

    String getName() {
        return this.name;
    }// setting the name of the employee

    void setName(String name) {
        this.name = name;
    }// setting the employee id

    // of the employee
    void setId(int a) {
        this.empId = a;
    }// retrieving the employee id of

    // the employee
    int getId() {
        return this.empId;
    }

    @Override
    public int compareTo(Employee o) {
        if(empId==o.empId)
        return 0;
        else if (empId>o.empId) {
            return 1;
        } else
            return -1;
    }
}