package com.example.demoprojectforspringsecurity.Employee;

public class Employee {

    private long employeeId;
    private String employeeName;

    public Employee(int employeeId, String employeeName){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public long getEmployeeId(){
        return employeeId;
    }

    public String getEmployeeName(){
        return employeeName;
    }

}
