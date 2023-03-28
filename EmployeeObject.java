package com.JDBCPayroll;

public class EmployeeObject {
double salary;
    public EmployeeObject(){}

    public EmployeeObject(Double pay) {
        this.salary = pay;
    }
    public double getSalary(){
        return this.salary;
    }
}
